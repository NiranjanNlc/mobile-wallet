package org.mifos.mobilewallet.mifospay.bank.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetUpUpiPinViewModel @Inject constructor(): ViewModel(){

    private val _setUpUpiPinEvent = MutableStateFlow<SetUpUpiPinEvent>(SetUpUpiPinEvent.Initial)
    val setUpUpiPinEvent: StateFlow<SetUpUpiPinEvent> = _setUpUpiPinEvent

}

sealed class SetUpUpiPinEvent {

    object Initial : SetUpUpiPinEvent()
    object SetUpUpiPinSuccess : SetUpUpiPinEvent()
    object SetUpUpiPinError : SetUpUpiPinEvent()
    object RequestOtpSuccess : SetUpUpiPinEvent()
    object RequestOtpError : SetUpUpiPinEvent()
}