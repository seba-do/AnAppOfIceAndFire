package com.ttdrp.gameofthrones.ui.general

import android.content.res.Configuration
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ttdrp.gameofthrones.utils.*

@Composable
fun DefaultTopAppBar(title: String, navigateUp: () -> Unit) =
    TopAppBar(title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = null
                )
            }
        }
    )

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
private fun DefaultTopAppBarPreview() = ThemedPreview {
    DefaultTopAppBar(title = "Title of Subscreen") {}
}

