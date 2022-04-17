package com.ttdrp.gameofthrones.ui.housedetails.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ttdrp.gameofthrones.ui.ThemedPreview

@Composable
fun Headlined(
    modifier: Modifier = Modifier,
    headline: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = headline,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h3
        )
        Divider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
        )
        content()
    }
}

@Preview
@Composable
private fun HouseInfoHeadlinePreview() {
    ThemedPreview {
        Headlined(headline = "Region") {}
    }
}