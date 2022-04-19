package com.ttdrp.gameofthrones.ui.housedetails.elements

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ttdrp.gameofthrones.utils.*
import com.ttdrp.gameofthrones.utils.ThemedPreview

@Composable
fun Link(
    linkText: String,
    modifier: Modifier = Modifier,
    onLinkClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable(onClick = onLinkClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = linkText, modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null
        )
    }
}

@Preview(
    name = "Link $PreviewLight",
    group = PreviewGroupLight,
    showBackground = true
)
@Preview(
    name = "Link $PreviewDark",
    group = PreviewGroupDark,
    showBackground = true,
    backgroundColor = PreviewBackgroundDark,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LinkPreview() = ThemedPreview {
    Link("Name of the Link") {}
}