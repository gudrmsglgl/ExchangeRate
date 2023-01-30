package com.wirebarley.domain.model

data class CurrencyResponse(
    val source: String,
    val success: Boolean,
    val timestamp: Long,
    val quotes: Quotes
)
