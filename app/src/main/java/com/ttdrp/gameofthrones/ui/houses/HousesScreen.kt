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
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.data.houses.HouseResponse
import com.ttdrp.gameofthrones.data.houses.housesMock
import com.ttdrp.gameofthrones.ui.ThemedPreview
import com.ttdrp.gameofthrones.viewmodels.HousesViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun HousesScreen(
    navController: NavController,
    housesViewModel: HousesViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    HousesScreen(
        houses = housesViewModel.pagingDataFlow.collectAsLazyPagingItems(),
        scaffoldState = scaffoldState
    ) {
        navController.navigate("house/$it")
    }
}


@Composable
private fun HousesScreen(
    houses: LazyPagingItems<HouseResponse>,
    scaffoldState: ScaffoldState,
    onHouseClick: (String) -> Unit
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
                empty = houses.itemCount == 0,
                emptyContent = { FullScreenLoading() }
            ) {
                HousesScreenErrorAndContent(
                    houses = houses,
                    modifier = modifier,
                    onHouseClick = onHouseClick
                )
            }
        }
    )
}

@Composable
private fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
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

/*
@Composable
private fun HousesScreenErrorAndContent(
    navController: NavController,
    houses: UiState<List<House>>,
    modifier: Modifier = Modifier
) {
    if (houses.data != null) {
        HousesList(
            houses = houses.data,
            modifier = modifier
        ) {
            navController.navigate("house/$it")
        }
    } else {
        Box(modifier.fillMaxSize()) {
            Text(text = "An unknown Error occurred")
        }
    }
}
*/

@Composable
private fun HousesScreenErrorAndContent(
    modifier: Modifier = Modifier,
    houses: LazyPagingItems<HouseResponse>,
    onHouseClick: (String) -> Unit
) {
    if (houses.itemCount != 0) {
        HousesList2(
            houses = houses,
            modifier = modifier,
            onHouseClick = onHouseClick
        )
    } else {
        Box(modifier.fillMaxSize()) {
            Text(text = "An unkown Error occurred")
        }
    }
}

@Composable
private fun HousesList(
    houses: List<HouseResponse>,
    modifier: Modifier = Modifier,
    onHouseClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        item {
            HousesSection(houses = houses) { onHouseClick(it) }
        }
    }
}

@Composable
private fun HousesList2(
    houses: LazyPagingItems<HouseResponse>,
    modifier: Modifier = Modifier,
    onHouseClick: (String) -> Unit
) {

    LazyColumn(modifier = modifier) {
        items(houses) { item ->
            item?.let { house ->
                HouseCard(
                    house = house
                ) { url ->
                    onHouseClick(url)
                }
                HouseListDivider()
            }
        }
        houses.apply {
            when {
                loadState.refresh is LoadState.Loading -> {}
                loadState.append is LoadState.Loading -> {}
                loadState.append is LoadState.Error -> {}
            }
        }
    }
}

@Composable
private fun HousesSection(
    houses: List<HouseResponse>,
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

@Composable
private fun HouseListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}


@Preview(name = "Houses screen body")
@Composable
fun PreviewHousesScreenBody() {
    ThemedPreview {
        HousesList(houses = housesMock) {}
    }
}

@Preview("Houses screen dark theme")
@Composable
fun PreviewHousesScreenBodyDark() {
    ThemedPreview(darkTheme = true) {
        HousesList(houses = housesMock) {}
    }
}