package com.wirebarley.network

import com.wirebarley.data.model.CurrencyResponseEntity
import com.wirebarley.data.source.HomeRemoteSource
import com.wirebarley.network.model.asEntity
import com.wirebarley.network.service.HomeService
import javax.inject.Inject

class HomeRemoteSourceImpl
@Inject
constructor(private val service: HomeService) : HomeRemoteSource {

    override suspend fun getCurrencyData(source: String): CurrencyResponseEntity {
        return service
            .getCurrencyData(source)
            .asEntity()
    }
}