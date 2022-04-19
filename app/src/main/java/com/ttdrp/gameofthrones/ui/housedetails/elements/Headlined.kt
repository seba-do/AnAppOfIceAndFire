package com.ttdrp.gameofthrones.ui.housedetails.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Male
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ttdrp.gameofthrones.utils.*

@Composable
fun Headlined(
    modifier: Modifier = Modifier,
    headline: String,
    icon: ImageVector? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row {
            Text(
                text = headline,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.weight(1f)
            )
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null
                )
            }
        }
        Divider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
        )
        content()
    }
}

@Preview(
    name = "House Info Headline $PreviewLight",
    group = PreviewGroupLight,
    showBackground = true
)
@Preview(
    name = "House Info Headline $PreviewDark",
    group = PreviewGroupDark,
    showBackground = true,
    backgroundColor = PreviewBackgroundDark,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HouseInfoHeadlinePreview() {
    ThemedPreview {
        Headlined(headline = "Region") {}
    }
}

@Preview(
    name = "House Info Headline $PreviewLight",
    group = PreviewGroupLight,
    showBackground = true
)
@Preview(
    name = "House Info Headline $PreviewDark",
    group = PreviewGroupDark,
    showBackground = true,
    backgroundColor = PreviewBackgroundDark,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HeadlineWithIconPreview() {
    ThemedPreview {
        Headlined(
            headline = "Headline",
            icon = Icons.Filled.Male
        ) {}
    }
}