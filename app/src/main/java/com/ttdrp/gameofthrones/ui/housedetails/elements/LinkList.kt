package com.ttdrp.gameofthrones.ui.housedetails.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ttdrp.gameofthrones.utils.*

@Composable
fun LinkList(
    modifier: Modifier = Modifier,
    list: List<Pair<String, String>>,
    onLinkClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        list.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Circle,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .size(8.dp)
                )
                Link(linkText = it.first) { onLinkClick(it.second.id()) }
            }
        }
    }
}

@Preview(
    name = "String List $PreviewLight",
    group = PreviewGroupLight,
    showBackground = true
)
@Preview(
    name = "String List $PreviewDark",
    group = PreviewGroupDark,
    showBackground = true,
    backgroundColor = PreviewBackgroundDark,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun StringListPreview() {
    ThemedPreview {
        LinkList(list = listOf(Pair("Ser", ""), Pair("Lord", ""), Pair("The Knight", ""))) {}
    }
}