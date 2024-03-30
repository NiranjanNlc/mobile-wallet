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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mifos.mobilewallet.mifospay.R
import org.mifos.mobilewallet.mifospay.theme.MifosTheme

@Composable
fun DebitCardScreen(
    modifier: Modifier = Modifier
)
{
    var cardNumber by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle value change */ },
            label = { Text(text = "Debit Card Number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType =
            KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onNext = { /* Handle next action */ }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            BasicTextField(
                modifier = modifier,
                value = TextFieldValue(month, selection = TextRange(month.length)),
                onValueChange = {
                     month = it.text
                },
                keyboardActions = KeyboardActions(onDone =
                {

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
                modifier = modifier,
                value = TextFieldValue(year, selection = TextRange(month.length)),
                onValueChange = {
                    year  = it.text
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                decorationBox = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        repeat(4) { index ->
                            CharView(
                                index = index,
                                text = month
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun DebitCardScreenPreview() {
    MifosTheme {
       DebitCardScreen(
        )
    }
}