package com.ttdrp.gameofthrones.ui.houses.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ttdrp.gameofthrones.data.houses.housesMock
import com.ttdrp.gameofthrones.database.HouseDatabase
import com.ttdrp.gameofthrones.utils.*

@Composable
fun HousesSection(
    houses: List<HouseDatabase>,
    onHouseClick: (String) -> Unit
) {
    Column {
        houses.forEach { house ->
            HouseCard(
                house = house
            ) {
                onHouseClick(it)
            }
            HouseListDivider()
        }
    }
}

@Preview(
    name = "House section $PreviewLight",
    group = PreviewGroupLight,
    showBackground = true
)
@Preview(
    name = "House section $PreviewDark",
    group = PreviewGroupDark,
    showBackground = true,
    backgroundColor = PreviewBackgroundDark,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewHousesScreenBody() = ThemedPreview {
    HousesSection(houses = housesMock) {}
}