package com.wirebarley.domain.interactor

import com.wirebarley.common.model.Result
import com.wirebarley.common.model.asResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseParamUseCase<PARAM, DOMAIN> {

    abstract val dispatcher: CoroutineDispatcher

    abstract suspend fun execute(param: PARAM): DOMAIN

    operator fun invoke(param: PARAM): Flow<Result<DOMAIN>> = flow {
        emit(execute(param))
    }
        .asResult()
        .flowOn(dispatcher)
}