package com.wirebarley.domain.interactor

import com.wirebarley.common.dispatcher.HiltDispatcher
import com.wirebarley.common.dispatcher.HiltDispatchers
import com.wirebarley.common.model.asResult
import com.wirebarley.domain.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetOtherCurrencyUseCase
@Inject constructor(
    private val repository: HomeRepository,
    @HiltDispatcher(HiltDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke() = flow {
        emit(repository.getOtherCurrencyData())
    }
        .asResult()
        .flowOn(ioDispatcher)
}