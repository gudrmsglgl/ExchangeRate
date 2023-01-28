package com.wirebarley.exchange.home.model

data class Quotes(
    val usdPhp: Double = 52.72027,
    val usdKrw: Double = 1121.419945,
    val usdJpy: Double = 110.959498
) {
    companion object {
        fun getExchangeRate(param: String): Double {
            val prefixIndex = param.indexOfFirst { it == '(' }
            val suffix = param.indexOfLast { it == ')' }
            val currencyCode = param.substring(prefixIndex.plus(1), suffix)

            return when (currencyCode) {
                "KRW" -> Quotes().usdKrw
                "PHP" -> Quotes().usdPhp
                else -> Quotes().usdJpy
            }
        }
    }
}
