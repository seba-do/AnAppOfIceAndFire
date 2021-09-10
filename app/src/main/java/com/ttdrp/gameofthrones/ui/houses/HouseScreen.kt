package com.ttdrp.gameofthrones.ui.houses

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.data.houses.impl.BlockingFakeHousesRepository
import com.ttdrp.gameofthrones.model.House
import com.ttdrp.gameofthrones.ui.state.UiState
import com.ttdrp.gameofthrones.ui.state.copyWithResult
import com.ttdrp.gameofthrones.ui.theme.GameOfThronesTheme
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
        item {
            HouseInformationItem(
                label = stringResource(id = R.string.house_region),
                value = { Text(text = house.region) },
                modifier = modifier
            )
        }
        item {
            HouseInformationItem(
                label = stringResource(id = R.string.house_coat_of_arms),
                value = { Text(text = house.coatOfArms) },
                modifier = modifier
            )
        }
        if (house.words.isNotEmpty()) {
            item {
                HouseInformationItem(
                    label = "",
                    value = { HouseWordQuote(quote = house.words) },
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun HouseInformationItem(
    label: String,
    value: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = 8.dp
    Column(modifier = modifier) {
        Text(text = label, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = spacing))
        value()
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f), modifier = Modifier.padding(top = spacing))
    }
}

@Composable
private fun HouseWordQuote(quote: String) {
    Row(
        modifier = Modifier
            .defaultMinSize(minHeight = 50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.FormatQuote,
            contentDescription = null,
            Modifier
                .align(Alignment.Bottom)
                .scale(0.6f)
        )
        Text(
            text = quote,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Icon(
            imageVector = Icons.Default.FormatQuote,
            contentDescription = null,
            modifier = Modifier
                .rotate(180f)
                .scale(0.6f)
        )
    }
}

@Preview("House screen")
@Preview("House screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewHouseScreen() {
    val house = runBlocking {
        UiState<House>().copyWithResult(BlockingFakeHousesRepository().getHouse("House Arryn of the Eyrie"))
    }
    val scaffoldState = rememberScaffoldState()

    GameOfThronesTheme {
        HouseScreen(house = house, scaffoldState = scaffoldState)
    }
}

@Preview("Quote")
@Composable
fun PreviewQuote() {
    HouseWordQuote(quote = "No Foe May Pass")
}