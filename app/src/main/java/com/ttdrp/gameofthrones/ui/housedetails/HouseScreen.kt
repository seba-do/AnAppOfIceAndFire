package com.ttdrp.gameofthrones.ui.housedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.data.Result
import com.ttdrp.gameofthrones.data.houses.HouseResponse
import com.ttdrp.gameofthrones.data.houses.house1
import com.ttdrp.gameofthrones.ui.ThemedPreview
import com.ttdrp.gameofthrones.ui.housedetails.elements.Headlined
import com.ttdrp.gameofthrones.ui.housedetails.elements.HouseWordQuote
import com.ttdrp.gameofthrones.ui.housedetails.elements.NoInformation
import com.ttdrp.gameofthrones.ui.housedetails.elements.StringList
import com.ttdrp.gameofthrones.ui.houses.FullScreenLoading
import com.ttdrp.gameofthrones.ui.state.UiState
import com.ttdrp.gameofthrones.utils.produceUiState
import com.ttdrp.gameofthrones.viewmodels.HouseViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun HouseScreen(
    navController: NavController,
    houseViewModel: HouseViewModel,
    houseName: String,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val (houseUiState) = produceUiState(houseViewModel) {
        houseViewModel.getHouse(houseName)
    }

    HouseScreen(
        house = houseUiState.value,
        scaffoldState = scaffoldState
    )
}

@Composable
fun HouseScreen(
    house: UiState<HouseResponse>,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            val title = house.data?.name ?: ""
            TopAppBar(
                title = { Text(text = title) }
            )
        },
        content = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            LoadingContent(
                empty = house.initialLoad,
                emptyContent = { FullScreenLoading() },
                loading = house.loading
            ) {
                HouseScreenErrorAndContent(house = house, modifier = modifier)
            }
        }
    )
}

@Composable
private fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
        content()
    }
}

@Composable
private fun HouseScreenErrorAndContent(
    house: UiState<HouseResponse>,
    modifier: Modifier = Modifier
) {
    if (house.data != null) {
        House(house.data, modifier)
    } else {
        Box(modifier.fillMaxSize()) {}
    }
}

@Composable
private fun House(
    house: HouseResponse,
    modifier: Modifier = Modifier
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
    }
}

@Preview
@Composable
fun PreviewHouseScreenBody() {
    ThemedPreview {
        House(house = house1)
    }
}
