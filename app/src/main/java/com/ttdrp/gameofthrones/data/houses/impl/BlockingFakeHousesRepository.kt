package com.ttdrp.gameofthrones.data.houses.impl

import com.ttdrp.gameofthrones.data.Result
import com.ttdrp.gameofthrones.data.houses.IHousesRepository
import com.ttdrp.gameofthrones.database.HousesDatabase
import com.ttdrp.gameofthrones.model.House
import com.ttdrp.gameofthrones.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlockingFakeHousesRepository @Inject constructor() : IHousesRepository {

    var houses: MutableStateFlow<List<House>> = MutableStateFlow(housesMock)

    override suspend fun getHouse(name: String): Result<House> {
        return withContext(Dispatchers.IO) {
            val house = housesMock.find { it.name == name }
            if (house == null) {
                Result.Error(IllegalArgumentException("Unable to find house"))
            } else {
                Result.Success(house)
            }
        }
    }

    override suspend fun getHouses(): Result<List<House>> = Result.Error(Exception("Test exception"))

    override suspend fun refreshHouses() = Result.Success(Unit)
}