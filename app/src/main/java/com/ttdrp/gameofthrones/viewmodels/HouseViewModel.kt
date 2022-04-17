package com.ttdrp.gameofthrones.viewmodels

import androidx.lifecycle.ViewModel
import com.ttdrp.gameofthrones.data.houses.HouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HouseViewModel @Inject constructor(
    private val repository: HouseRepository
) : ViewModel() {

    suspend fun getHouse(name: String) = repository.getHouse(name)
}