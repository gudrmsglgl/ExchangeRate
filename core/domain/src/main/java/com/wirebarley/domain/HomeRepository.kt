package com.wirebarley.domain

import com.wirebarley.domain.model.CurrencyResponse

interface HomeRepository {
    suspend fun getCurrencyData(source: String = "USD"): CurrencyResponse
}