package com.ttdrp.gameofthrones.ui.state

import com.ttdrp.gameofthrones.data.Result

data class UiState<T>(
    val loading: Boolean = false,
    val exception: Exception? = null,
    val data: T? = null
) {
    val hasError: Boolean
        get() = exception != null

    val initialLoad: Boolean
        get() = data == null && loading && !hasError
}

fun <T> UiState<T>.copyWithResult(value: Result<T>): UiState<T> {
    return when (value) {
        is Result.Uninitialized -> copy(loading = false)
        is Result.Success -> copy(loading = false, exception = null, data = value.data)
        is Result.Error -> copy(loading = false, exception = value.exception)
    }
}