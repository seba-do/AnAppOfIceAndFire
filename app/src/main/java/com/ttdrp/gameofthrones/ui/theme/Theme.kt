package com.ttdrp.gameofthrones.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Brand,
    primaryVariant = BrandHigh,
    secondary = Highlight,
    secondaryVariant = HighLightLow,
    background = White,
    surface = White,
    error = Alert,
    onPrimary = White,
    onSecondary = White,
    onBackground = Text,
    onSurface = Text,
    onError = White
)

private val DarkColorPalette = darkColors(
    primary = Brand,
    primaryVariant = BrandHigh,
    secondary = Highlight,
    secondaryVariant = HighLightLow,
    background = Text,
    surface = Text,
    error = Alert,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White,
    onError = White
)

@Composable
fun GameOfThronesTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}