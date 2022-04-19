package com.ttdrp.gameofthrones.ui.houses

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.data.houses.HouseResponse
import com.ttdrp.gameofthrones.data.houses.housesMock
import com.ttdrp.gameofthrones.database.HouseDatabase
import com.ttdrp.gameofthrones.ui.LocalNavController
import com.ttdrp.gameofthrones.ui.LocalTopAppBarController
import com.ttdrp.gameofthrones.ui.general.FullScreenError
import com.ttdrp.gameofthrones.ui.general.FullScreenLoading
import com.ttdrp.gameofthrones.ui.houses.elements.HouseCard
import com.ttdrp.gameofthrones.ui.houses.elements.HouseListDivider
import com.ttdrp.gameofthrones.utils.*
import com.ttdrp.gameofthrones.viewmodels.HousesViewModel
import kotlinx.coroutines.flow.flowOf

@Composable
fun HousesScreen(
    housesViewModel: HousesViewModel,
) {
    val navController = LocalNavController.current
    val topAppBarController = LocalTopAppBarController.current

    topAppBarController.update { HousesScreenTopBar() }

    HousesScreen(
        houses = housesViewModel.pagingDataFlow.collectAsLazyPagingItems()
    ) {
        navController.navigate("house/$it")
    }
}

@Composable
private fun HousesScreenTopBar() {
    val title = stringResource(id = R.string.app_name)
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_gameofthrones_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(ratio = 0.9f)
                    .padding(start = 12.dp)
            )
        }
    )

}

@Composable
private fun HousesScreen(
    houses: LazyPagingItems<HouseDatabase>,
    onHouseClick: (String) -> Unit
) {
    LoadingContent(
        empty = houses.itemCount == 0,
        emptyContent = { FullScreenLoading() }
    ) {
        HousesScreenErrorAndContent(
            houses = houses,
            onHouseClick = onHouseClick
        )
    }
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
private fun HousesScreenErrorAndContent(
    modifier: Modifier = Modifier,
    houses: LazyPagingItems<HouseDatabase>,
    onHouseClick: (String) -> Unit
) {
    if (houses.itemCount != 0) {
        HousesList(
            houses = houses,
            modifier = modifier,
            onHouseClick = onHouseClick
        )
    } else {
        FullScreenError(errorMessage = "Houses are empty")
    }
}

@Composable
private fun HousesList(
    houses: LazyPagingItems<HouseDatabase>,
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
    }
}

@Preview(
    name = "Houses screen $PreviewLight",
    group = PreviewGroupLight,
    showBackground = true
)
@Preview(
    name = "Houses screen $PreviewDark",
    group = PreviewGroupDark,
    showBackground = true,
    backgroundColor = PreviewBackgroundDark,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun HousesScreenTopBarPreview() = ThemedPreview {
    HousesScreenTopBar()
}

@Preview(
    name = "Houses screen $PreviewLight",
    group = PreviewGroupLight,
    showBackground = true
)
@Preview(
    name = "Houses screen $PreviewDark",
    group = PreviewGroupDark,
    showBackground = true,
    backgroundColor = PreviewBackgroundDark,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewHousesScreenBody() = ThemedPreview {
    HousesList(houses = flowOf(PagingData.from(housesMock)).collectAsLazyPagingItems()) {}
}