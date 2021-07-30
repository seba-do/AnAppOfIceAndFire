package com.ttdrp.gameofthrones.ui.houses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.data.Result
import com.ttdrp.gameofthrones.data.houses.IHousesRepository
import com.ttdrp.gameofthrones.data.houses.impl.BlockingFakeHousesRepository
import com.ttdrp.gameofthrones.model.House
import com.ttdrp.gameofthrones.ui.Screen
import com.ttdrp.gameofthrones.ui.ThemedPreview
import com.ttdrp.gameofthrones.ui.state.UiState
import com.ttdrp.gameofthrones.utils.produceUiState
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun HousesScreen(
    navigateTo: (Screen) -> Unit,
    housesRepository: IHousesRepository,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val (houseUiState, refreshHouse, clearError) = produceUiState(housesRepository) {
        getHouses()
    }

    val coroutineScope = rememberCoroutineScope()

    HousesScreen(
        houses = houseUiState.value,
        navigateTo = navigateTo,
        scaffoldState = scaffoldState
    )
}

@Composable
fun HousesScreen(
    houses: UiState<List<House>>,
    navigateTo: (Screen) -> Unit,
    scaffoldState: ScaffoldState
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            val title = stringResource(id = R.string.app_name)
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_gameofthrones_logo),
                            contentDescription = ""
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            LoadingContent(
                empty = houses.initialLoad,
                emptyContent = { FullScreenLoading() },
                loading = houses.loading,
                content = {
                    HousesScreenErrorAndContent(
                        houses = houses,
                        navigateTo = navigateTo,
                        modifier = modifier
                    )
                }
            )
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
fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun HousesScreenErrorAndContent(
    houses: UiState<List<House>>,
    navigateTo: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    if (houses.data != null) {
        HousesList(houses.data, navigateTo, modifier)
    } else {
        Box(modifier.fillMaxSize()) {}
    }
}

@Composable
private fun HousesList(
    houses: List<House>,
    navigateTo: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item { HousesSection(houses = houses, navigateTo) }
    }
}

@Composable
private fun HousesSection(
    houses: List<House>,
    navigateTo: (Screen) -> Unit
) {
    Column {
        houses.forEach { house ->
            HouseCard(
                house = house,
                navigateTo = navigateTo
            )
            HouseListDivider()
        }
    }
}

@Composable
private fun HouseListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}


@Preview("Houses screen body")
@Composable
fun PreviewHousesScreenBody() {
    ThemedPreview {
        val houses = loadMockHouses()
        HousesList(houses = houses, {})
    }
}

@Preview("Houses screen dark theme")
@Composable
fun PreviewHousesScreenBodyDark() {
    ThemedPreview(darkTheme = true) {
        val houses = loadMockHouses()
        HousesList(houses = houses, {})
    }
}

@Composable
private fun loadMockHouses(): List<House> {
    val houses = runBlocking {
        BlockingFakeHousesRepository().getHouses()
    }
    return (houses as Result.Success).data
}