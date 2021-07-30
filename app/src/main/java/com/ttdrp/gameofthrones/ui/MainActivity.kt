package com.ttdrp.gameofthrones.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ttdrp.gameofthrones.IceAndFireApplication
import com.ttdrp.gameofthrones.ui.theme.GameOfThronesTheme

class MainActivity : ComponentActivity() {

    private val navigationViewModel by viewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as IceAndFireApplication).container
        setContent {
            IceAndFireApp(appContainer = appContainer, navigationViewModel = navigationViewModel)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!navigationViewModel.onBack()) {
            super.onBackPressed()
        }
    }
}