package com.ttdrp.gameofthrones.ui.lorddetails

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ttdrp.gameofthrones.R
import com.ttdrp.gameofthrones.model.LordResolved
import com.ttdrp.gameofthrones.ui.LocalNavController
import com.ttdrp.gameofthrones.ui.LocalTopAppBarController
import com.ttdrp.gameofthrones.ui.general.FullScreenError
import com.ttdrp.gameofthrones.ui.general.FullScreenLoading
import com.ttdrp.gameofthrones.ui.general.LoadingContent
import com.ttdrp.gameofthrones.ui.housedetails.elements.Headlined
import com.ttdrp.gameofthrones.ui.housedetails.elements.NoInformation
import com.ttdrp.gameofthrones.ui.housedetails.elements.StringList
import com.ttdrp.gameofthrones.ui.state.UiState
import com.ttdrp.gameofthrones.utils.*
import com.ttdrp.gameofthrones.viewmodels.LordViewModel

@Composable
fun LordScreen(
    lordViewModel: LordViewModel,
    lordId: String
) {
    val navController = LocalNavController.current
    val topAppBarController = LocalTopAppBarController.current

    val (lordUiState) = produceUiState(lordViewModel) {
        lordViewModel.getResolvedLord(lordId)
    }

    topAppBarController.update {
        LordScreenTopBar(
            title = lordUiState.value.data?.name.orEmpty(),
            dead = lordUiState.value.data?.died?.isNotBlank() ?: false
        ) {
            navController.navigateUp()
        }
    }

    LordScreen(
        lord = lordUiState.value,
        onHouseClick = { navController.navigate("house/$it") },
        onLordClick = { navController.navigate("lord/$it") }
    )
}

@Composable
private fun LordScreenTopBar(title: String, dead: Boolean, navigateUp: () -> Unit) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = title, modifier = Modifier.weight(1f))
                if (dead) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cross),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun LordScreen(
    lord: UiState<LordResolved>,
    onHouseClick: (String) -> Unit,
    onLordClick: (String) -> Unit
) {
    LoadingContent(
        loading = lord.initialLoad,
        loadingContent = { FullScreenLoading() }
    ) {
        LordScreenErrorAndContent(
            lord = lord,
            onHouseClick = onHouseClick,
            onLordClick = onLordClick
        )
    }
}

@Composable
private fun LordScreenErrorAndContent(
    lord: UiState<LordResolved>,
    modifier: Modifier = Modifier,
    onHouseClick: (String) -> Unit,
    onLordClick: (String) -> Unit
) {
    if (lord.data != null) {
        Lord(
            lord = lord.data,
            modifier = modifier,
            onHouseClick = onHouseClick,
            onLordClick = onLordClick
        )
    } else {
        FullScreenError(errorMessage = lord.exception?.message.orEmpty())
    }
}

@Composable
private fun Lord(
    lord: LordResolved,
    modifier: Modifier = Modifier,
    onHouseClick: (String) -> Unit,
    onLordClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Headlined(headline = "Titles") {
                if (lord.titles.isNotEmpty() && lord.titles.first().isNotBlank()) {
                    StringList(list = lord.titles)
                } else {
                    NoInformation()
                }
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
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LordScreenTopBarPreview() = ThemedPreview {
    LordScreenTopBar(title = "Any ", true) {}
}