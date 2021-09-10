package com.ttdrp.gameofthrones.ui.houses

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.data.houses.impl.houseDiedOut
import com.ttdrp.gameofthrones.model.House
import com.ttdrp.gameofthrones.ui.theme.GameOfThronesTheme


@Composable
fun HouseName(house: House) {
    Text(house.name, style = MaterialTheme.typography.subtitle1)
}

@Composable
fun RegionName(house: House) {
    Text(house.region, style = MaterialTheme.typography.subtitle2)
}

@Composable
fun HouseStatusImage(house: House, modifier: Modifier = Modifier) {
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
    house: House,
    navigateToHouse: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable(onClick = {
                navigateToHouse("house/${house.name}")
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

@Preview("House died out")
@Preview("House died out (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HouseCardPreview() {
    val house = houseDiedOut

    GameOfThronesTheme {
        Surface {
            HouseCard(house = house) {}
        }
    }
}