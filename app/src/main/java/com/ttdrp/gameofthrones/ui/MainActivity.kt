package com.ttdrp.gameofthrones.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val navigationViewModel by viewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IceAndFireApp()
        }
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        if (!navigationViewModel.onBack()) {
//            super.onBackPressed()
//        }
//    }
}