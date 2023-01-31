package com.wirebarley.data.source

import com.wirebarley.data.model.CurrencyResponseEntity

interface HomeRemoteSource {
    suspend fun getCurrencyData(source: String = "USD"): CurrencyResponseEntity
}