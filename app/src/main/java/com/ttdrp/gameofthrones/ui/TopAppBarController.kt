package com.ttdrp.gameofthrones.ui

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TopAppBarController {
    private val _current = MutableStateFlow<@Composable () -> Unit> {}
    val current = _current.asStateFlow()

    fun update(topBar: @Composable () -> Unit) {
        _current.value = topBar
    }
}