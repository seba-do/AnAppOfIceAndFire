package com.ttdrp.gameofthrones.ui.lorddetails

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ttdrp.gameofthrones.ui.LocalNavController
import com.ttdrp.gameofthrones.ui.LocalTopAppBarController
import com.ttdrp.gameofthrones.ui.general.DefaultTopAppBar
import com.ttdrp.gameofthrones.utils.produceUiState
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
        DefaultTopAppBar(title = lordUiState.value.data?.name ?: "") {
            navController.navigateUp()
        }
    }

    Text("Lord Page in progress")
}

@Composable
private fun LordScreen(
    onLordClick: (String) -> Unit
) {
}