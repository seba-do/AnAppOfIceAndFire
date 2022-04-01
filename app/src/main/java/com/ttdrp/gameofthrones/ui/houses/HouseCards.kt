package com.ttdrp.gameofthrones.ui.houses

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
import com.ttdrp.gameofthrones.data.houses.HouseResponse
import com.ttdrp.gameofthrones.data.houses.houseDiedOut
import com.ttdrp.gameofthrones.ui.ThemedPreview


@Composable
fun HouseName(house: HouseResponse) {
    Text(house.name, style = MaterialTheme.typography.subtitle1)
}

@Composable
fun RegionName(house: HouseResponse) {
    Text(house.region, style = MaterialTheme.typography.subtitle2)
}

@Composable
fun HouseStatusImage(house: HouseResponse, modifier: Modifier = Modifier) {
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
    house: HouseResponse,
    onHouseClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = {
                onHouseClick(house.name)
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

@Preview("House card")
@Composable
fun HouseCardPreview() {
    val house = houseDiedOut

    ThemedPreview {
//        HouseCard(house = house, {})
    }
}