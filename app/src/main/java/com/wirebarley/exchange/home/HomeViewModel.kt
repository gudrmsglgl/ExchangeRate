package com.wirebarley.exchange.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wirebarley.common.model.Result
import com.wirebarley.data.source.HomeRemoteSource
import com.wirebarley.domain.HomeRepository
import com.wirebarley.domain.interactor.GetCurrencyUseCase
import com.wirebarley.domain.interactor.GetOtherCurrencyUseCase
import com.wirebarley.domain.model.CurrencyResponse
import com.wirebarley.network.service.HomeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val getOtherCurrencyUseCase: GetOtherCurrencyUseCase
) : ViewModel() {

    fun requestCurrency(source: String): StateFlow<Result<CurrencyResponse>> {
        return getCurrencyUseCase(source)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading)
    }

    val currencyResponse = getOtherCurrencyUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading)

}