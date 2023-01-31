package com.wirebarley.network.service

import com.wirebarley.network.model.NetworkCurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("live")
    suspend fun getCurrencyData(@Query("source") source: String = "USD"): NetworkCurrencyResponse
}