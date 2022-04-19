package com.ttdrp.gameofthrones.utils

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.ttdrp.gameofthrones.ui.theme.GameOfThronesTheme

@Composable
internal fun ThemedPreview(
    content: @Composable () -> Unit
) {
    GameOfThronesTheme {
        Surface {
            content()
        }
    }
}

const val PreviewLight = "(Light)"
const val PreviewDark = "(Dark)"
const val PreviewGroupLight = "Light"
const val PreviewGroupDark = "Dark"
const val PreviewBackgroundDark =  0x00000000L