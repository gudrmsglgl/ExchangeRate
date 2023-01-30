package com.wirebarley.network.di

import com.wirebarley.data.source.HomeRemoteSource
import com.wirebarley.network.HomeRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteSourceModule {
    @Binds
    fun bindHomeRemoteSource(remoteSourceImpl: HomeRemoteSourceImpl): HomeRemoteSource
}