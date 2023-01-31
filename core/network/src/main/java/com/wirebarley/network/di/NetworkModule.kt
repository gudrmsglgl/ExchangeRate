package com.wirebarley.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.wirebarley.network.service.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val baseUrl = "https://api.apilayer.com/currency_data/"

    @Provides
    fun provideSerializationJson(): Json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    fun provideRetrofit2(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(
                @OptIn(ExperimentalSerializationApi::class)
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    @Provides
    fun provideOkHttp(): OkHttpClient {
        val interceptor = makeLoggingInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(interceptor)
            .build()
    }

    private fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    class AuthInterceptor : Interceptor {
        @kotlin.jvm.Throws
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("apikey","oqjY7BkNlzvBnaOp8FoUw2YGZdgOS1Hs")
                .build()
            proceed(newRequest)
        }
    }

}