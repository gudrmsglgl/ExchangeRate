package com.wirebarley.data.model

import com.wirebarley.domain.model.CurrencyResponse

data class CurrencyResponseEntity(
    val source: String,
    val success: Boolean,
    val timestamp: Long,
    val quotes: QuotesEntity
)

fun CurrencyResponseEntity.asDomain() = CurrencyResponse(
    source = this.source,
    success = this.success,
    timestamp = this.timestamp,
    quotes = this.quotes.asDomain()
)