package com.ttdrp.gameofthrones.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ttdrp.gameofthrones.data.houses.impl.HousesRepository
import com.ttdrp.gameofthrones.model.House
import com.ttdrp.gameofthrones.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseViewModel @Inject constructor(
    private val repository: HousesRepository
) : ViewModel() {

    suspend fun getHouse(name: String) = repository.getHouse(name)
}