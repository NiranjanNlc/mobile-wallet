package org.mifos.mobilewallet.mifospay.bank.fragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mifos.mobilewallet.mifospay.R
import org.mifos.mobilewallet.mifospay.theme.MifosTheme

@Composable
fun UpiPinScreen(
    realOtp: String,
    onOtpTextCorrectlyEntered: () -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.enter_upi_pin),
            color = Color(0xFF1E90FF),
            fontSize = 18.sp
        )
        OtpTextField(
            modifier = Modifier.padding(top = 20.dp),
            realOtp = realOtp,
            onOtpTextCorrectlyEntered = onOtpTextCorrectlyEntered
        )
    }
}

@Preview
@Composable
fun UpiScreenPreview() {
    MifosTheme {
        UpiPinScreen(
            realOtp = "1234",
            onOtpTextCorrectlyEntered = {}
        )
    }
}