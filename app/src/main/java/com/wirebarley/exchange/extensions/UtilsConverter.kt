package com.wirebarley.exchange.extensions

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Long.convertTimestampToDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return dateFormat.format(this * 1000L)
}

fun Double.convertExchangeRate(): String {
    val priceFormat = DecimalFormat("#,###.##")
    return priceFormat.format(this)
}

fun String.extractCurrencyCode(): String {
    if (this.find { it == '(' } == null) throw Exception("this is not country string value")
    val prefixIndex = this.indexOfFirst { it == '(' }
    val suffix = this.indexOfLast { it == ')' }
    return this.substring(prefixIndex.plus(1), suffix)
}