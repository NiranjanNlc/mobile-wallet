package org.mifos.mobilewallet.mifospay.bank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import dagger.hilt.android.AndroidEntryPoint
import org.mifos.mobilewallet.mifospay.bank.ui.SetupUpiPinActivity
import org.mifos.mobilewallet.mifospay.base.BaseFragment
import org.mifos.mobilewallet.mifospay.common.Constants
import org.mifos.mobilewallet.mifospay.theme.MifosTheme

/**
 * Created by ankur on 13/July/2018
 */
@AndroidEntryPoint
class OtpFragment : BaseFragment() {

    private var otp: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            otp = requireArguments().getString(Constants.OTP)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                MifosTheme {
                    OtpScreen(realOtp =otp ?: "",
                        onOtpTextCorrectlyEntered ={
                        onOtpTextCorrectlyEntered()
                    })
                }
            }
        }
    }

    fun onOtpTextCorrectlyEntered() {
        if (activity is SetupUpiPinActivity) {
                (activity as SetupUpiPinActivity?)!!.otpVerified()
        }
    }


    companion object {
        fun newInstance(otp: String?): OtpFragment {
            val args = Bundle()
            args.putString(Constants.OTP, otp)
            val fragment = OtpFragment()
            fragment.arguments = args
            return fragment
        }
    }
}