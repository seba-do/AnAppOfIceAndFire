package com.ttdrp.gameofthrones.ui.houses.elements

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.data.houses.houseDiedOut
import com.ttdrp.gameofthrones.database.HouseDatabase
import com.ttdrp.gameofthrones.utils.*


@Composable
fun HouseName(house: HouseDatabase) {
    Text(house.name, style = MaterialTheme.typography.subtitle1)
}

@Composable
fun RegionName(house: HouseDatabase) {
    Text(house.region, style = MaterialTheme.typography.subtitle2)
}

@Composable
fun HouseStatusImage(house: HouseDatabase, modifier: Modifier = Modifier) {
    if (house.diedOut.isNotEmpty()) {
        Image(
            painter = painterResource(id = R.drawable.ic_cross),
            contentDescription = null,
            modifier = modifier
                .size(40.dp, 40.dp)
                .clip(MaterialTheme.shapes.small)
                .padding(5.dp)
        )
    }

}

@Composable
fun HouseCard(
    house: HouseDatabase,
    onHouseClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = {
                onHouseClick(house.id)
            })
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            HouseName(house = house)
            RegionName(house = house)
        }
        HouseStatusImage(house = house)
    }
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
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun HouseCardPreview() {
    val house = houseDiedOut

    ThemedPreview {
        HouseCard(house = house) {}
    }
}