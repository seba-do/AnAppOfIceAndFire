package com.ttdrp.gameofthrones.ui.housedetails.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ttdrp.gameofthrones.ui.ThemedPreview

@Composable
fun StringList(
    modifier: Modifier = Modifier,
    list: List<String>
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
                Text(text = it)
            }
        }
    }
}

@Preview
@Composable
private fun StringListPreview() {
    ThemedPreview {
        StringList(list = listOf("Ser", "Lord", "The Knight"))
    }
}