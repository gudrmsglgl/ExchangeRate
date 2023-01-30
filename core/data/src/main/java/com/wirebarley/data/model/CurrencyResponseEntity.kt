package com.wirebarley.data.model

data class CurrencyResponseEntity(
    val source: String,
    val success: Boolean,
    val timestamp: Long,
    val quotes: QuotesEntity
)
