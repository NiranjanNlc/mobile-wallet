package org.mifos.mobilewallet.mifospay.bank.fragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.mifos.mobilewallet.mifospay.bank.presenter.DebitCardUiState
import org.mifos.mobilewallet.mifospay.bank.presenter.DebitCardViewModel
import org.mifos.mobilewallet.mifospay.bank.ui.SetupUpiPinActivity
import org.mifos.mobilewallet.mifospay.common.Constants
import org.mifos.mobilewallet.mifospay.designsystem.component.MfLoadingWheel
import org.mifos.mobilewallet.mifospay.theme.MifosTheme
import org.mifos.mobilewallet.mifospay.utils.Toaster.showToast

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DebitCardScreen(
    viewModel: DebitCardViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onDebitCardVerified: (String) -> Unit,
    onDebitCardVerificationFailed:(String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

    var cardNumber by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    val debitCardUiState by viewModel.debitCardUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        val (a, b, c) = FocusRequester.createRefs()

        OutlinedTextField(
            value = cardNumber,
            onValueChange = { cardNumber = it },
            label = { Text(text = "Debit Card Number") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(a)
                .focusProperties {
                    next = b
                }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            BasicTextField(
                modifier = Modifier
                    .focusRequester(b)
                    .focusProperties {
                        next = c
                    },
                value = TextFieldValue(month, selection = TextRange(month.length)),
                onValueChange = {
                    month = it.text
                    if (month.length == 2) {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                },
                keyboardActions = KeyboardActions(onDone =
                {
                    focusManager.moveFocus(FocusDirection.Next)

                }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                decorationBox = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        repeat(2) { index ->
                            CharView(
                                index = index,
                                text = month
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            )
            Text(
                text = "/",
                fontSize = 30.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            BasicTextField(
                modifier = Modifier
                    .focusRequester(c)
                    .focusProperties {
                        next = a
                    },
                value = TextFieldValue(year, selection = TextRange(year.length)),
                onValueChange = {
                    year = it.text
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone =
                {
                    viewModel.verifyDebitCard(cardNumber, month, year)
                }),
                decorationBox = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        repeat(4) { index ->
                            CharView(
                                index = index,
                                text = year
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            )
        }
    }
    when (debitCardUiState) {
        is DebitCardUiState.Initials -> {
            // do nothing
        }

        is DebitCardUiState.Verifying -> {
            MfLoadingWheel(
                contentDesc = Constants.PLEASE_WAIT,
                backgroundColor = Color.White
            )
        }

        is DebitCardUiState.Verified -> {
            onDebitCardVerified((debitCardUiState as DebitCardUiState.Verified).otp)
        }

        is DebitCardUiState.VerificationFailed -> {
            onDebitCardVerificationFailed((debitCardUiState as DebitCardUiState.VerificationFailed).errorMessage)
        }
    }
}

@Preview
@Composable
fun DebitCardScreenPreview() {
    MifosTheme {
        DebitCardScreen(
            onDebitCardVerified = {},
            onDebitCardVerificationFailed = {}
        )
    }
}