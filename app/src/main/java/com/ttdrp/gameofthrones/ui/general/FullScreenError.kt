package com.ttdrp.gameofthrones.ui.general

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ttdrp.gameofthrones.utils.*

private const val defaultError = "An unknown error occurred"
@Composable
fun FullScreenError(
    modifier: Modifier = Modifier,
    errorMessage: String = ""
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage.takeIf { it.isNotBlank() } ?: defaultError)
    }
}

@Preview(
    name = "Houses screen $PreviewLight",
    group = PreviewGroupLight,
    showBackground = true
)
@Preview(
    name = "Houses screen $PreviewDark",
    group = PreviewGroupDark,
    showBackground = true,
    backgroundColor = PreviewBackgroundDark,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun FullScreenErrorPreview() = ThemedPreview {
    FullScreenError()
}