package com.ttdrp.gameofthrones.ui.houses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.data.Result
import com.ttdrp.gameofthrones.data.houses.IHousesRepository
import com.ttdrp.gameofthrones.data.houses.impl.BlockingFakeHousesRepository
import com.ttdrp.gameofthrones.model.House
import com.ttdrp.gameofthrones.ui.ThemedPreview
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
    house: UiState<House>,
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
    house: UiState<House>,
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
    house: House,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item { HouseInformation(house = house, modifier = Modifier.padding(bottom = 10.dp)) }
    }
}

@Composable
private fun HouseInformation(
    house: House,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        HouseInformationItem(
            label = stringResource(id = R.string.house_region),
            value = house.region,
            modifier = modifier
        )
        HouseInformationItem(
            label = stringResource(id = R.string.house_coat_of_arms),
            value = house.coatOfArms,
            modifier = modifier
        )
        HouseInformationItem(
            label = stringResource(id = R.string.house_words),
            value = house.words,
            modifier = modifier
        )
    }
}

@Composable
private fun HouseInformationItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Text(text = value, modifier = modifier)
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f))
    }
}

@Composable
private fun HouseWordQuote() {

}

@Preview
@Composable
fun PreviewHouseScreenBody() {
    ThemedPreview {
        val house = loadMockHouse()
        House(house = house)
    }
}

@Composable
private fun loadMockHouse(): House {
    val house = runBlocking {
        BlockingFakeHousesRepository().getHouse("House Arryn of the Eyrie")
    }
    return (house as Result.Success).data
}