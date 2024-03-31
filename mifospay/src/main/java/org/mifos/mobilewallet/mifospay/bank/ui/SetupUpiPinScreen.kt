package org.mifos.mobilewallet.mifospay.bank.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mifos.mobilewallet.model.domain.BankAccountDetails
import org.mifos.mobilewallet.mifospay.R
import org.mifos.mobilewallet.mifospay.common.Constants
import org.mifos.mobilewallet.mifospay.theme.MifosTheme

@Composable
fun SetupUpiPinScreen() {
    val context = LocalContext.current
    var bankAccountDetails by remember { mutableStateOf(BankAccountDetails("SBI", "Ankur Sharma", "New Delhi", "XXXXXXXX9990XXX", "Savings")) }
    var index by remember { mutableStateOf(0) }
    var type by remember { mutableStateOf(Constants.CHANGE) }

    var debitCardVerified by remember { mutableStateOf(false) }
    var otpVerified by remember { mutableStateOf(false) }
    var upiPinEntered by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text(text = "Setup UPI Pin") },
            navigationIcon = {
                IconButton(onClick = { /* Handle back button click */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        if (!debitCardVerified) {
            DebitCardFragment(onDebitCardVerified = {
                debitCardVerified = true
            })
        } else if (!otpVerified) {
            OtpFragment(onOtpVerified = {
                otpVerified = true
            })
        } else if (!upiPinEntered) {
            UpiPinFragment(onUpiPinEntered = { upiPin ->
                upiPinEntered = true
                setupUpiPin(context, bankAccountDetails, upiPin)
            })
        }
    }
}

@Composable
fun DebitCardFragment(onDebitCardVerified: () -> Unit) {
    // Implement the UI for verifying debit card
    // Call onDebitCardVerified when debit card is verified
}

@Composable
fun OtpFragment(onOtpVerified: () -> Unit) {
    // Implement the UI for OTP verification
    // Call onOtpVerified when OTP is verified
}

@Composable
fun UpiPinFragment(onUpiPinEntered: (String) -> Unit) {
    // Implement the UI for entering UPI Pin
    // Call onUpiPinEntered with the entered UPI Pin
}

fun setupUpiPin(context: Context, bankAccountDetails: BankAccountDetails, upiPin: String) {
    // Implement the setup UPI Pin logic here
    // This function should handle setting up the UPI Pin and related actions
}
@Preview
@Composable
fun PreviewSetupUpiPinScreen() {
    MifosTheme {
        SetupUpiPinScreen()
    }
}