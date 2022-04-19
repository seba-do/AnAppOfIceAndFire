package com.ttdrp.gameofthrones.ui.general

import androidx.compose.runtime.Composable

@Composable
fun LoadingContent(
    loading: Boolean,
    loadingContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (loading) {
        loadingContent()
    } else {
        content()
    }
}