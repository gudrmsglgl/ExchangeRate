package com.wirebarley.exchange.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.ListItemPicker
import com.wirebarley.exchange.R
import com.wirebarley.exchange.extensions.addFocusCleaner
import com.wirebarley.exchange.extensions.convertExchangeRate
import com.wirebarley.exchange.extensions.convertTimestampToDate
import com.wirebarley.exchange.extensions.extractCurrencyCode
import com.wirebarley.exchange.home.model.Quotes
import com.wirebarley.exchange.ui.theme.ExchangeRateTheme


@Composable
fun HomeScreen() {
    val focusManager = LocalFocusManager.current

    val currencyNames = listOf("한국(KRW)", "일본(JPY)", "필리핀(PHP)")
    var currencyName by rememberSaveable { mutableStateOf(currencyNames[0]) }

    var remittance by rememberSaveable { mutableStateOf("") }

    val amountReceived: Double? by remember(currencyName, remittance) {
        val exchangeRate = Quotes.getExchangeRate(currencyName)
        if (remittance.isEmpty())
            return@remember mutableStateOf(null)
        if (remittance.toIntOrNull() == null)
            return@remember mutableStateOf(null)

        val result = remittance.toInt() * exchangeRate
        return@remember mutableStateOf(result)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
            .addFocusCleaner(focusManager)
    ) {

        Title(
            text = stringResource(id = R.string.topbar_name),
            fontSize = 40.sp
        )

        ExChangeInfoContents(
            modifier = Modifier.padding(top = 20.dp, start = 30.dp),
            currencyName = currencyName,
            remittance = remittance,
            exchangeRate = Quotes.getExchangeRate(currencyName),
            onRemittanceChange = {
                remittance = it
            }
        )

        AnimatedVisibility(
            visible = !isValidRemittance(remittance),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = stringResource(id = R.string.remittance_error_message),
                textAlign = TextAlign.Center,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
        }

        ReceiptAmountResult(
            result = amountReceived?.convertExchangeRate() ?: "",
            currencyName = currencyName,
            isVisible = remittance.isNotEmpty() && isValidRemittance(remittance)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            ListItemPicker(
                modifier = Modifier.fillMaxWidth(),
                label = { it },
                value = currencyName,
                onValueChange = {
                    focusManager.clearFocus()
                    currencyName = it
                },
                list = currencyNames,
                dividersColor = Color.LightGray
            )
        }

    }
}

@Composable
private fun Title(
    text: String,
    fontSize: TextUnit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            fontSize = fontSize,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun ExChangeInfoContents(
    modifier: Modifier = Modifier,
    currencyName: String,
    remittance: String,
    exchangeRate: Double = 1229.389997,
    timeStamp: Long = 1674821343,
    onRemittanceChange: (String) -> Unit
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        RemittanceCountryRow()
        RecipientCountryRow(currencyName)
        ExchangeRateRow(
            exchangeRate = exchangeRate,
            currencyName = currencyName
        )
        RequestTimeRow(timeStamp = timeStamp)
        RemittanceInputRow(
            key = stringResource(id = R.string.remittance),
            value = remittance,
            onValueChange = onRemittanceChange,
        )

    }

}

@Composable
private fun RemittanceCountryRow() {
    RowInfo(
        key = stringResource(id = R.string.remittance_country),
        value = stringResource(id = R.string.remittance_country_usd)
    )
}

@Composable
private fun RecipientCountryRow(recipientCountry: String) {
    RowInfo(
        key = stringResource(id = R.string.recipient_country),
        value = recipientCountry
    )
}

@Composable
private fun ExchangeRateRow(
    exchangeRate: Double,
    currencyName: String
) {
    val currencyCode = currencyName.extractCurrencyCode()

    RowInfo(
        key = stringResource(id = R.string.exchange_rate),
        value = "${exchangeRate.convertExchangeRate()} $currencyCode / USD"
    )
}

@Composable
private fun ReceiptAmountResult(
    result: String,
    currencyName: String,
    isVisible: Boolean
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Text(
            text = stringResource(R.string.receipt_amount_info, result, currencyName.extractCurrencyCode()),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
            ,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun RequestTimeRow(timeStamp: Long) {
    RowInfo(
        key = stringResource(id = R.string.request_time),
        value = timeStamp.convertTimestampToDate()
    )
}

@Composable
private fun RemittanceInputRow(
    key: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("$key : ", modifier = Modifier.width(70.dp), textAlign = TextAlign.End)
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .wrapContentHeight(Alignment.Top)
                .width(120.dp)
                .padding(top = 3.dp, end = 10.dp)
                .border(width = 1.dp, Color.LightGray)
                .align(Alignment.Top)
            ,
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(color = MaterialTheme.colors.secondary, textAlign = TextAlign.End)
        )
        Text("USD")
    }
}

@Composable
private fun RowInfo(
    key: String,
    value: String
) {
    Row {
        Text("$key : ", modifier = Modifier.width(70.dp), textAlign = TextAlign.End)
        Text(value)
    }
}


@Preview(showBackground = true)
@Composable
private fun TitlePreview() {
    ExchangeRateTheme {
        Title(
            text = stringResource(id = R.string.topbar_name),
            fontSize = 40.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RemittanceCountryRowPreview() {
    ExchangeRateTheme {
        RemittanceCountryRow()
    }
}

@Preview(showBackground = true)
@Composable
fun RecipientCountryRowPreview() {
    ExchangeRateTheme {
        RecipientCountryRow("한국 (KRW)")
    }
}

@Preview(showBackground = true)
@Composable
fun ExchangeRateRowPreview() {
    ExchangeRateTheme {
        ExchangeRateRow(
            exchangeRate = 1229.389997,
            currencyName = "한국 (KRW)"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RequestTimeRowPreview() {
    ExchangeRateTheme {
        RequestTimeRow(timeStamp = 1674821343)
    }
}

@Preview(showBackground = true)
@Composable
fun RemittanceInputRowPreview() {
    ExchangeRateTheme {
        var remittance by remember {
            mutableStateOf("")
        }
        RemittanceInputRow(
            key = stringResource(id = R.string.remittance),
            value = remittance,
            onValueChange = {
                remittance = it
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReceiptAmountResultPreview() {
    val currencyName = "한국(KRW)"
    val exchangeRate = Quotes.getExchangeRate(currencyName)
    val result = 100 * exchangeRate
    ExchangeRateTheme {
        ReceiptAmountResult(
            result = result.convertExchangeRate(),
            currencyName = currencyName,
            isVisible = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RowInfoPreview() {
    ExchangeRateTheme {
        RowInfo(
            key = stringResource(id = R.string.remittance_country),
            value = stringResource(id = R.string.remittance_country_usd)
        )
    }
}


private fun isValidRemittance(param: String): Boolean {
    if (param.isEmpty()) return true
    if (param.toIntOrNull() == null) return false
    val result = param.toInt()
    return (result > 0) && (result < 10000)
}



