package org.mifos.mobilewallet.mifospay.bank.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import butterknife.BindView
import butterknife.ButterKnife
import dagger.hilt.android.AndroidEntryPoint
import com.mifos.mobilewallet.model.domain.BankAccountDetails
import org.mifos.mobilewallet.mifospay.R
import org.mifos.mobilewallet.mifospay.bank.BankContract
import org.mifos.mobilewallet.mifospay.bank.BankContract.SetupUpiPinView
import org.mifos.mobilewallet.mifospay.bank.fragment.DebitCardFragment
import org.mifos.mobilewallet.mifospay.bank.fragment.OtpFragment
import org.mifos.mobilewallet.mifospay.bank.fragment.UpiPinFragment
import org.mifos.mobilewallet.mifospay.bank.presenter.SetupUpiPinPresenter
import org.mifos.mobilewallet.mifospay.base.BaseActivity
import org.mifos.mobilewallet.mifospay.utils.AnimationUtil
import org.mifos.mobilewallet.mifospay.common.Constants
import org.mifos.mobilewallet.mifospay.databinding.ActivitySetupUpiPinBinding
import org.mifos.mobilewallet.mifospay.utils.Toaster
import javax.inject.Inject

@AndroidEntryPoint
class SetupUpiPinActivity : BaseActivity(), SetupUpiPinView {
    @JvmField
    @Inject
    var mPresenter: SetupUpiPinPresenter? = null
    var mSetupUpiPinPresenter: BankContract.SetupUpiPinPresenter? = null

    private var bankAccountDetails: BankAccountDetails? = null
    private var index = 0
    private var type: String? = null

    lateinit var binding : ActivitySetupUpiPinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupUpiPinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showColoredBackButton(R.drawable.ic_arrow_back_black_24dp)
        setToolbarTitle(Constants.SETUP_UPI_PIN)
        mPresenter!!.attachView(this)
        val b = intent.extras
//        bankAccountDetails = b!!.getParcelable(Constants.BANK_ACCOUNT_DETAILS)
//        index = b.getInt(Constants.INDEX)
//        type = b.getString(Constants.TYPE)
        initialize(b)
        if (type == Constants.CHANGE) {
            mSetupUpiPinPresenter!!.requestOtp(bankAccountDetails)
            binding.flDebitCard!!.visibility = View.GONE
            binding.cvDebitCard!!.visibility = View.GONE
            setToolbarTitle(Constants.CHANGE_UPI_PIN)
        } else if (type == Constants.FORGOT) {
            binding.flDebitCard!!.visibility = View.VISIBLE
            binding.cvDebitCard!!.visibility = View.VISIBLE
            setToolbarTitle(Constants.FORGOT_UPI_PIN)
        } else {
            binding.flDebitCard!!.visibility = View.VISIBLE
            binding.cvDebitCard!!.visibility = View.VISIBLE
            setToolbarTitle(Constants.SETUP_UPI_PIN)
        }
        addFragment(DebitCardFragment(), R.id.fl_debit_card)
    }

    private fun initialize(b: Bundle?) {
        bankAccountDetails =  BankAccountDetails(
            "SBI", "Ankur Sharma", "New Delhi",
            "XXXXXXXX9990XXX " + " ", "Savings"
        )
        index = 0
        type =Constants.FORGOT
    }

    override fun debitCardVerified(otp: String?) {
        binding.tvDebitCard!!.visibility = View.VISIBLE
        addFragment(OtpFragment.newInstance(otp), R.id.fl_otp)
        binding.flDebitCard?.let { AnimationUtil.collapse(it) }
        binding.flOtp?.let { AnimationUtil.expand(it) }
    }

    fun otpVerified() {
        binding.tvOtp!!.visibility = View.VISIBLE
        addFragment(UpiPinFragment(), R.id.fl_upi_pin)
        binding.flUpiPin?.let { AnimationUtil.expand(it) }
        binding.flOtp?.let { AnimationUtil.collapse(it) }
    }

    fun upiPinEntered(upiPin: String?) {
        replaceFragment(UpiPinFragment.newInstance(1, upiPin), false, R.id.fl_upi_pin)
    }

    fun upiPinConfirmed(upiPin: String?) {
        binding.tvUpi!!.visibility = View.VISIBLE
        showProgressDialog(Constants.SETTING_UP_UPI_PIN)
        mSetupUpiPinPresenter!!.setupUpiPin(bankAccountDetails, upiPin)
    }

    override fun setupUpiPinSuccess(mSetupUpiPin: String?) {
        bankAccountDetails!!.isUpiEnabled = true
        bankAccountDetails!!.upiPin = mSetupUpiPin
        hideProgressDialog()
        showToast(Constants.UPI_PIN_SETUP_COMPLETED_SUCCESSFULLY)
        val intent = Intent()
        intent.putExtra(Constants.UPDATED_BANK_ACCOUNT, bankAccountDetails)
        intent.putExtra(Constants.INDEX, index)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun setupUpiPinError(message: String?) {
        hideProgressDialog()
        showToast(Constants.ERROR_WHILE_SETTING_UP_UPI_PIN)
    }

    override fun setPresenter(presenter: BankContract.SetupUpiPinPresenter?) {
        mSetupUpiPinPresenter = presenter
    }

    fun showToast(message: String?) {
        Toaster.showToast(this, message)
    }
}