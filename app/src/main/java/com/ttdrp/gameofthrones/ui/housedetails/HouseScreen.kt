package com.ttdrp.gameofthrones.ui.housedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.data.houses.house1Resolved
import com.ttdrp.gameofthrones.model.HouseResolved
import com.ttdrp.gameofthrones.ui.LocalNavController
import com.ttdrp.gameofthrones.ui.LocalTopAppBarController
import com.ttdrp.gameofthrones.ui.general.DefaultTopAppBar
import com.ttdrp.gameofthrones.ui.general.FullScreenError
import com.ttdrp.gameofthrones.ui.general.FullScreenLoading
import com.ttdrp.gameofthrones.ui.general.LoadingContent
import com.ttdrp.gameofthrones.ui.housedetails.elements.*
import com.ttdrp.gameofthrones.ui.state.UiState
import com.ttdrp.gameofthrones.utils.ThemedPreview
import com.ttdrp.gameofthrones.utils.produceUiState
import com.ttdrp.gameofthrones.viewmodels.HouseViewModel

@Composable
fun HouseScreen(
    houseViewModel: HouseViewModel,
    houseId: String,
) {
    val navController = LocalNavController.current
    val topAppBarController = LocalTopAppBarController.current

    val (houseUiState) = produceUiState(houseViewModel) {
        houseViewModel.getResolvedHouse(houseId)
    }

    topAppBarController.update {
        DefaultTopAppBar(title = houseUiState.value.data?.name ?: "") {
            navController.navigateUp()
        }
    }

    HouseScreen(
        house = houseUiState.value,
        onHouseClick = { navController.navigate("house/$it") },
        onLordClick = { navController.navigate("lord/$it") }
    )

}

@Composable
private fun HouseScreen(
    house: UiState<HouseResolved>,
    onHouseClick: (String) -> Unit,
    onLordClick: (String) -> Unit
) {
    LoadingContent(
        empty = house.initialLoad,
        emptyContent = { FullScreenLoading() },
    ) {
        HouseScreenErrorAndContent(
            house = house,
            onHouseClick = onHouseClick,
            onLordClick = onLordClick
        )
    }
}

@Composable
private fun HouseScreenErrorAndContent(
    house: UiState<HouseResolved>,
    modifier: Modifier = Modifier,
    onHouseClick: (String) -> Unit,
    onLordClick: (String) -> Unit
) {
    if (house.data != null) {
        House(
            house = house.data,
            modifier = modifier,
            onHouseClick = onHouseClick,
            onLordClick = onLordClick
        )
    } else {
        FullScreenError(errorMessage = house.exception?.message ?: "")
    }
}

@Composable
private fun House(
    house: HouseResolved,
    modifier: Modifier = Modifier,
    onHouseClick: (String) -> Unit,
    onLordClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        if (house.words.isNotBlank()) {
            item {
                HouseWordQuote(quote = house.words)
                Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f))
            }
        }
        item {
            Headlined(headline = stringResource(R.string.house_region)) {
                if (house.region.isNotBlank()) {
                    Text(text = house.region)
                } else {
                    NoInformation()
                }
            }
        }
        item {
            Headlined(headline = stringResource(id = R.string.house_coat_of_arms)) {
                if (house.coatOfArms.isNotBlank()) {
                    Text(text = house.coatOfArms)
                } else {
                    NoInformation()
                }
            }
        }
        item {
            Headlined(headline = "Titles") {
                if (house.titles.isNotEmpty() && house.titles.first().isNotBlank()) {
                    StringList(list = house.titles)
                } else {
                    NoInformation()
                }
            }
        }
        item {
            Headlined(headline = "Seats") {
                if (house.seats.isNotEmpty() && house.seats.first().isNotBlank()) {
                    StringList(list = house.seats)
                } else {
                    NoInformation()
                }
            }
        }

        item {
            Headlined(headline = "Current Lord") {
                if (house.currentLord != null) {
                    Link(linkText = house.currentLord.name) { onLordClick(house.currentLord.id) }
                } else {
                    NoInformation()
                }
            }
        }
        item {
            Headlined(headline = "Heir") {
                if (house.heir != null) {
                    Link(linkText = house.heir.name) { onLordClick(house.heir.id) }
                } else {
                    NoInformation()
                }
            }
        }
        item {
            Headlined(headline = "Overlord") {
                if (house.overlord != null) {
                    Link(linkText = house.overlord.name) { onHouseClick(house.overlord.id) }
                } else {
                    NoInformation()
                }
            }
        }
        item {
            Headlined(headline = "Founded") {
                if (house.founded.isNotBlank()) {
                    Text(text = house.founded)
                } else {
                    NoInformation()
                }
            }
        }
        item {
            Headlined(headline = "Founder") {
                if(house.founder != null) {
                    Link(linkText = house.founder.name) { onLordClick(house.founder.id) }
                } else {
                    NoInformation()
                }
            }
        }
        item {
            Headlined(headline = "Ancestral Weapons") {
                if (house.ancestralWeapons.isNotEmpty() && house.ancestralWeapons.first().isNotBlank()){
                    StringList(list = house.ancestralWeapons)
                } else {
                    NoInformation()
                }
            }
        }
        item {
            Headlined(headline = "Cadet Branches") {
                if (house.cadetBranches != null) {
                    LinkList(
                        list = house.cadetBranches.map { Pair(it.name, it.id) },
                        onLinkClick = onHouseClick
                    )
                } else {
                    NoInformation()
                }
            }
        }
        item {
            Headlined(headline = "Sworn Members") {
                if (house.swornMembers != null) {
                    LinkList(
                        list = house.swornMembers.map { Pair(it.name, it.id) },
                        onLinkClick = onLordClick
                    )
                } else {
                    NoInformation()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHouseScreenBody() {
    ThemedPreview {
        House(
            house = house1Resolved,
            onHouseClick = {},
            onLordClick = {}
        )
    }
}
