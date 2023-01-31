package com.wirebarley.common.dispatcher.di

import com.wirebarley.common.dispatcher.HiltDispatcher
import com.wirebarley.common.dispatcher.HiltDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @HiltDispatcher(HiltDispatchers.IO)
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

}