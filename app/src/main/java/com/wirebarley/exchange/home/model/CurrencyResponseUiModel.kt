package com.wirebarley.exchange.home.model

import com.wirebarley.domain.model.CurrencyResponse
import com.wirebarley.domain.model.Quotes
import com.wirebarley.exchange.extensions.extractCurrencyCode

data class CurrencyResponseUiModel(
    val source: String,
    val success: Boolean,
    val timestamp: Long,
    val quotes: Quotes
) {
    fun getExchangeRate(param: String): Double {
        return when (param.extractCurrencyCode()) {
            "KRW" -> quotes.usdKrw
            "PHP" -> quotes.usdPhp
            else -> quotes.usdJpy
        }
    }
}

fun CurrencyResponse.asUiModel() = CurrencyResponseUiModel(
    source = this.source,
    success = this.success,
    timestamp = this.timestamp,
    quotes = this.quotes
)
