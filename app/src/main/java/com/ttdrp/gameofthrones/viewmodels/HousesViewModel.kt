package com.ttdrp.gameofthrones.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ttdrp.gameofthrones.data.houses.impl.BlockingFakeHousesRepository
import com.ttdrp.gameofthrones.data.houses.impl.HousesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HousesViewModel @Inject constructor(
    private val repository: HousesRepository
) : ViewModel() {

    val houses = repository.houses

    init {
        refreshDataFromRepository()
    }

    suspend fun getHouses() = repository.getHouses()

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            repository.refreshHouses()
        }
    }
}