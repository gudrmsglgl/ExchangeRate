package com.wirebarley.domain.interactor

import com.wirebarley.common.dispatcher.HiltDispatcher
import com.wirebarley.common.dispatcher.HiltDispatchers
import com.wirebarley.domain.HomeRepository
import com.wirebarley.domain.model.CurrencyResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCurrencyUseCase
@Inject constructor(
    private val repository: HomeRepository,
    @HiltDispatcher(HiltDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : BaseParamUseCase<String, CurrencyResponse>() {

    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override suspend fun execute(param: String): CurrencyResponse {
        return repository.getCurrencyData(param)
    }
}