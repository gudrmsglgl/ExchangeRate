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

    var count = 0
    fun requestCurrency(source: String): SharedFlow<Result<CurrencyResponse>> {
        count++
        Log.e("HomeViewModel", "requestCurrency count is $count")
        return getCurrencyUseCase(source)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(5000))
    }

    val currencyResponse = getOtherCurrencyUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading)

}