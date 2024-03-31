package org.mifos.mobilewallet.mifospay.bank.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import butterknife.BindView
import com.alimuzaffar.lib.pin.PinEntryEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.mifos.mobilewallet.mifospay.R
import org.mifos.mobilewallet.mifospay.bank.presenter.DebitCardUiState
import org.mifos.mobilewallet.mifospay.bank.presenter.DebitCardViewModel
import org.mifos.mobilewallet.mifospay.bank.ui.SetupUpiPinActivity
import org.mifos.mobilewallet.mifospay.base.BaseFragment
import org.mifos.mobilewallet.mifospay.common.Constants
import org.mifos.mobilewallet.mifospay.databinding.FragmentDebitCardBinding
import org.mifos.mobilewallet.mifospay.utils.Toaster

/**
 * Created by ankur on 13/July/2018
 */
@AndroidEntryPoint
class DebitCardFragment : BaseFragment(){

    private val debitCardViewModel: DebitCardViewModel by viewModels()


    lateinit var binding : FragmentDebitCardBinding
    var key = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //DebitCardScreen as jetpack Compose

        return ComposeView(requireContext()).apply {
            setContent {
                DebitCardScreen()
            }
//        binding = FragmentDebitCardBinding.inflate(inflater, container, false)
//        binding.peYear!!.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                okayClicked()
//                return@OnEditorActionListener true
//            }
//            false
//        })
//        binding.peMonth!!.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                if (s.length == 2) {
//                    binding.peYear!!.requestFocus()
//                }
//            }
//
//            override fun afterTextChanged(s: Editable) {}
//        })
//        binding.peYear!!.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
//            if (keyCode == KeyEvent.KEYCODE_DEL && binding.peYear!!.length() == 0) {
//                if (key) {
//                    binding.peMonth!!.requestFocus()
//                    binding.peMonth!!.dispatchKeyEvent(
//                        KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)
//                    )
//                    key = false
//                    return@OnKeyListener false
//                }
//                key = true
//            }
//            false
//        })
//        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewLifecycleOwner.lifecycleScope.launch {
//            debitCardViewModel.debitCardUiState.collect { uiState ->
//                when (uiState) {
//                    is DebitCardUiState.Initials -> {
//                        // do nothing
//                    }
//
//                    is DebitCardUiState.Verifying -> {
//                        showProgressDialog(Constants.PLEASE_WAIT)
//                    }
//
//                    is DebitCardUiState.Verified -> {
//                        hideProgressDialog()
//                        if (activity is SetupUpiPinActivity) {
//                            (activity as SetupUpiPinActivity?)!!.debitCardVerified(uiState.otp)
//                        }
//                    }
//
//                    is DebitCardUiState.VerificationFailed -> {
//                        hideProgressDialog()
//                        binding.etDebitCardNumber!!.requestFocusFromTouch()
//                        showToast(uiState.errorMessage)
//                    }
//                }
//            }
//        }
    }

    private fun okayClicked() {
        showProgressDialog(Constants.PLEASE_WAIT)
        debitCardViewModel.verifyDebitCard(binding.etDebitCardNumber!!.text
            .toString().trim { it <= ' ' },
            binding.peMonth!!.text.toString(),
            binding.peYear!!.text.toString()
        )
    }
    fun showToast(message: String?) {
        Toaster.showToast(activity, message)
    }
}