package com.wirebarley.exchange.extensions

import androidx.compose.runtime.Composable
import com.wirebarley.common.model.Result

@Composable
fun <T: Any> Result<T>.processOnComposable(
    onSuccess: @Composable (data: T) -> Unit = {},
    onLoading: @Composable () -> Unit = {},
    onError: @Composable (exception: Throwable?) -> Unit = {}
) {
    when (this) {
        is Result.Loading -> onLoading()
        is Result.Error -> onError(this.exception)
        is Result.Success -> onSuccess(this.data)
    }
}