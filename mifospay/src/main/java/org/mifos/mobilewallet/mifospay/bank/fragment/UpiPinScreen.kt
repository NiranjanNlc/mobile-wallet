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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mifos.mobilewallet.mifospay.R
import org.mifos.mobilewallet.mifospay.theme.MifosTheme

@Composable
fun UpiPinScreen(
    onOtpTextCorrectlyReEntered: (String) -> Unit,
    onOtpTextInCorrectlyReEntered: (String) -> Unit
)
{
    var upiPinTobeMatched by remember { mutableStateOf("") }
    var upiPin by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (steps == 0)
                stringResource(id = R.string.enter_upi_pin)
            else
                stringResource(id = R.string.reenter_upi),
            color = Color(0xFF1E90FF),
            fontSize = 18.sp
        )
        if (steps == 0) {
            BasicTextField(
                value = TextFieldValue(upiPin, selection = TextRange(upiPin.length)),
                onValueChange = {
                    upiPin = it.text
                    if (upiPin.length == 4) {
                        steps = 1
                    }
                },
                keyboardActions = KeyboardActions(onDone =
                {
                    if (upiPin.length == 4) {
                        onOtpTextCorrectlyReEntered(upiPin)
                    }
                }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                decorationBox = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        repeat(4) { index ->
                            CharView(
                                index = index,
                                text = upiPin
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
        else
        {
            BasicTextField(
                value = TextFieldValue(upiPinTobeMatched, selection = TextRange(upiPinTobeMatched.length)),
                onValueChange = {
                    upiPinTobeMatched = it.text
                    if (upiPinTobeMatched.length == 4) {
                        if (upiPinTobeMatched == upiPin) {
                            onOtpTextCorrectlyReEntered(upiPin)
                        }
                        else
                        {
                            onOtpTextCorrectlyReEntered("Invalid UPI PIN")
                        }
                    }
                },
                keyboardActions = KeyboardActions(onDone =
                {
                    if (upiPinTobeMatched.length == 4) {
                        if (upiPinTobeMatched == upiPin) {
                            onOtpTextCorrectlyReEntered(upiPin)
                        }
                    }
                    else
                    {
                        onOtpTextInCorrectlyReEntered("Invalid UPI PIN")
                    }
                }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                decorationBox = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        repeat(4) { index ->
                            CharView(
                                index = index,
                                text = upiPinTobeMatched
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun UpiScreenPreview() {
    MifosTheme {
        UpiPinScreen(
            onOtpTextCorrectlyReEntered = {},
            onOtpTextInCorrectlyReEntered = {}
        )
    }
}