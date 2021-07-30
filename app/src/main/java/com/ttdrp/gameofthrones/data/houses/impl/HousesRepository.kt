package com.ttdrp.gameofthrones.data.houses.impl

import com.ttdrp.gameofthrones.data.Result
import com.ttdrp.gameofthrones.data.houses.IHousesRepository
import com.ttdrp.gameofthrones.model.House
import com.ttdrp.gameofthrones.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HousesRepository(val apiService: ApiService) : IHousesRepository {

    override suspend fun getHouse(name: String): Result<House> {
        return withContext(Dispatchers.IO) {
            try {
                val house = apiService.getHouses().find { it.name == name }

                if (house == null) {
                    Result.Error(IllegalArgumentException("Unable to find house"))
                } else {
                    Result.Success(house)
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun getHouses(): Result<List<House>> {
        return withContext(Dispatchers.IO) {
            try {
                val houses = apiService.getHouses()
                Result.Success(houses)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}