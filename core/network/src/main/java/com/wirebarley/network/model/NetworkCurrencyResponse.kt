package com.wirebarley.network.model

@kotlinx.serialization.Serializable
data class NetworkCurrencyResponse(
    val source: String,
    val success: Boolean,
    val timestamp: Long,
    val quotes: NetworkQuotes
)
