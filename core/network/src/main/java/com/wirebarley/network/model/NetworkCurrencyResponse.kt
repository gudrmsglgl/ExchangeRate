package com.wirebarley.network.model

import com.wirebarley.data.model.CurrencyResponseEntity

@kotlinx.serialization.Serializable
data class NetworkCurrencyResponse(
    val source: String,
    val success: Boolean,
    val timestamp: Long,
    val quotes: NetworkQuotes
)

fun NetworkCurrencyResponse.asEntity() = CurrencyResponseEntity(
    source = this.source,
    success = this.success,
    timestamp = this.timestamp,
    quotes = this.quotes.asEntity()
)