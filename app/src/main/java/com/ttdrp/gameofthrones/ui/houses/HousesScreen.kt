package com.ttdrp.gameofthrones.ui.houses

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.data.houses.impl.BlockingFakeHousesRepository
import com.ttdrp.gameofthrones.model.House
import com.ttdrp.gameofthrones.ui.Screen
import com.ttdrp.gameofthrones.ui.ThemedPreview
import com.ttdrp.gameofthrones.ui.state.UiState
import com.ttdrp.gameofthrones.ui.theme.GameOfThronesTheme
import com.ttdrp.gameofthrones.utils.produceUiState
import com.ttdrp.gameofthrones.viewmodels.HousesViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun HousesScreen(
    navController: NavController,
    housesViewModel: HousesViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val (houseUiState) = produceUiState(housesViewModel) {
        housesViewModel.getHouses()
    }

    HousesScreen(
        uiState = houseUiState.value,
        scaffoldState = scaffoldState,
        navigateToHouse = { navController.navigate(it) }
    )
}

@Composable
fun HousesScreen(
    uiState: UiState<List<House>>,
    scaffoldState: ScaffoldState,
    navigateToHouse: (String) -> Unit
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
                empty = uiState.initialLoad,
                emptyContent = { FullScreenLoading() },
                loading = uiState.loading,
                content = {
                    HousesScreenErrorAndContent(
                        houses = uiState,
                        modifier = modifier,
                        navigateToHouse = navigateToHouse
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
    modifier: Modifier = Modifier,
    navigateToHouse: (String) -> Unit
) {
    if (houses.data != null) {
        HousesList(
            houses = houses.data,
            modifier = modifier,
            navigateToHouse = navigateToHouse
        )
    } else {
        Box(modifier.fillMaxSize()) {
            Text(text = "An unknown Error occurred")
        }
    }
}

@Composable
private fun HousesList(
    houses: List<House>,
    modifier: Modifier = Modifier,
    navigateToHouse: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        item { HousesSection(houses = houses, navigateToHouse = navigateToHouse) }
    }
}

@Composable
private fun HousesSection(
    houses: List<House>,
    navigateToHouse: (String) -> Unit
) {
    Column {
        houses.forEach { house ->
            HouseCard(
                house = house,
                navigateToHouse = navigateToHouse
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


@Preview("Houses screen")
@Preview("Houses screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewHousesScreen() {
    val houses = runBlocking { BlockingFakeHousesRepository().houses.value }
    GameOfThronesTheme {
        val scaffoldState = rememberScaffoldState()
        HousesScreen(
            uiState = UiState(data = houses),
            scaffoldState = scaffoldState,
        ) {}
    }
}