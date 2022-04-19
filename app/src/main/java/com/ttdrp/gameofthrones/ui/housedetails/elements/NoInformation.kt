package com.ttdrp.gameofthrones.ui.housedetails.elements

import android.content.res.Configuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ttdrp.gameofthrones.utils.*

@Composable
fun NoInformation() {
    Text(text = "** No available information **")
}

@Preview(
    name = "Houses card $PreviewLight",
    group = PreviewGroupLight,
    showBackground = true
)
@Preview(
    name = "Houses card $PreviewDark",
    group = PreviewGroupDark,
    showBackground = true,
    backgroundColor = PreviewBackgroundDark,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun NoInformationPreview() = ThemedPreview {
    NoInformation()
}