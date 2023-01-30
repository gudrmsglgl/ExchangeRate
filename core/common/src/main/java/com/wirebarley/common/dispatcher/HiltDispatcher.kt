package com.wirebarley.common.dispatcher

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HiltDispatcher(val dispatchers: HiltDispatchers)

enum class HiltDispatchers {
    IO
}