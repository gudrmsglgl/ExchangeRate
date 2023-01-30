package com.wirebarley.data

import com.wirebarley.data.model.asDomain
import com.wirebarley.data.source.HomeRemoteSource
import com.wirebarley.domain.HomeRepository
import com.wirebarley.domain.model.CurrencyResponse
import javax.inject.Inject

class HomeRepositoryImpl
@Inject constructor(
    private val remoteSource: HomeRemoteSource
) : HomeRepository {

    override suspend fun getCurrencyData(source: String): CurrencyResponse {
        return remoteSource
            .getCurrencyData()
            .asDomain()
    }
}