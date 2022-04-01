package com.ttdrp.gameofthrones.ui.housedetails.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.ui.ThemedPreview

@Composable
fun HouseWordQuote(
    quote: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        HouseWordQuoteIcon()
        Text(
            text = quote,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .sizeIn(maxWidth = 250.dp)
        )
        HouseWordQuoteIcon()
    }
}

@Composable
private fun HouseWordQuoteIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_quote),
        contentDescription = null,
        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
        alignment = Alignment.TopCenter,
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .sizeIn(minWidth = 30.dp)
            .fillMaxHeight()
    )
}

@Preview
@Composable
fun PreviewWordQuote() {
    ThemedPreview {
        HouseWordQuote(quote = "This is an absurd long quote, which feels like it would never end so we can test, how multiple lines look and how it behaves")
    }
}