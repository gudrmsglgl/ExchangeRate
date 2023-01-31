package com.wirebarley.network.service

import com.wirebarley.network.model.NetworkCurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("live")
    suspend fun getCurrencyData(@Query("source") source: String = "USD"): NetworkCurrencyResponse

    @GET("live")
    suspend fun getOtherCurrencyData(@Query("access_key") key: String = "ee50cd7cc73c9b7a7bb3d9617cfb6b9c"): NetworkCurrencyResponse
}