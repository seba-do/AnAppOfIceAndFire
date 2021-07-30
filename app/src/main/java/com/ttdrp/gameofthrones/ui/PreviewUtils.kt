package com.ttdrp.gameofthrones.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.ttdrp.gameofthrones.ui.theme.GameOfThronesTheme

@Composable
internal fun ThemedPreview(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    GameOfThronesTheme(darkTheme = darkTheme) {
        Surface {
            content()
        }
    }
}