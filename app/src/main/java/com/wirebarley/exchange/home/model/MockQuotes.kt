package com.wirebarley.exchange.home.model

import com.wirebarley.exchange.extensions.extractCurrencyCode

data class MockQuotes(
    val usdPhp: Double = 52.72027,
    val usdKrw: Double = 1121.419945,
    val usdJpy: Double = 110.959498
) {
    companion object {
        fun getExchangeRate(param: String): Double {
            return when (param.extractCurrencyCode()) {
                "KRW" -> MockQuotes().usdKrw
                "PHP" -> MockQuotes().usdPhp
                else -> MockQuotes().usdJpy
            }
        }
    }
}
