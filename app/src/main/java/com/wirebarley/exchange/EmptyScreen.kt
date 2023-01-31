package com.wirebarley.exchange

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wirebarley.common.model.Result
import com.wirebarley.exchange.home.HomeViewModel
import com.wirebarley.exchange.home.model.Sources
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.flow.takeWhile

@Composable
fun EmptyScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Log.e("Screen", "Screen start>>")

    val response = viewModel
        .requestCurrency(Sources.USD.toString())
        .collectAsStateWithLifecycle(initialValue = Result.Loading)
        .value
    var errorCount = 0
    when (response) {
        is Result.Error -> {
            errorCount++
            Text(text = "[$errorCount] exception : ${response.exception}")
        }
        is Result.Loading -> {
            Text(text = "Loading...")
        }
        is Result.Success -> {
            Text(text = "Success data is ${response.data.quotes}")
        }
    }


    /*val response = viewModel.currencyResponse
        .collectAsStateWithLifecycle(Result.Loading)
        .value

    when (response) {
        is Result.Error -> {
            Text("Error is ${response.exception}")
        }
        is Result.Loading -> {
            Text("Loading...")
        }
        is Result.Success -> {
            Text("Success data is ${response.data.quotes}")
        }
    }*/
}