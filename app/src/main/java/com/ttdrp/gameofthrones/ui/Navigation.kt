package com.ttdrp.gameofthrones.ui

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ttdrp.gameofthrones.ui.ScreenName.*
import com.ttdrp.gameofthrones.utils.getMutableStateOf

enum class ScreenName { OVERVIEW, HOUSE, LORD }

sealed class Screen(val id: ScreenName) {
    object Overview: Screen(OVERVIEW)
    data class House(val name: String) : Screen(HOUSE)
    data class Lord(val url: String) : Screen(LORD)
}

private const val SIS_SCREEN = "sis_screen"
private const val SIS_NAME = "screen_name"
private const val SIS_HOUSE = "house"
private const val SIS_LORD = "lord"

private fun Screen.toBundle(): Bundle {
    return bundleOf(SIS_NAME to id.name).also {
        if (this is Screen.House) {
            it.putString(SIS_HOUSE, name)
        }
    }
}

private fun Bundle.toScreen(): Screen {
    val screenName = ScreenName.valueOf(getStringOrThrow(SIS_NAME))
    return when (screenName) {
        OVERVIEW -> Screen.Overview
        HOUSE -> {
            val houseName = getStringOrThrow(SIS_HOUSE)
            Screen.House(houseName)
        }
        LORD -> {
            val lordName = getStringOrThrow(SIS_LORD)
            Screen.Lord(lordName)
        }
    }
}

private fun Bundle.getStringOrThrow(key: String) =
    requireNotNull(getString(key)) { "Missing key '$key' in $this" }

class NavigationViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var currentScreen: Screen by savedStateHandle.getMutableStateOf<Screen>(
        key = SIS_SCREEN,
        default = Screen.Overview,
        save = { it.toBundle() },
        restore = { it.toScreen() }
    )
        private set

    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreen != Screen.Overview
        currentScreen = Screen.Overview
        return wasHandled
    }

    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }
}