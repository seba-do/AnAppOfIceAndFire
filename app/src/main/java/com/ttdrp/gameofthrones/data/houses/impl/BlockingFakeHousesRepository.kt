package com.ttdrp.gameofthrones.data.houses.impl

import com.ttdrp.gameofthrones.data.Result
import com.ttdrp.gameofthrones.data.houses.IHousesRepository
import com.ttdrp.gameofthrones.model.House
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BlockingFakeHousesRepository : IHousesRepository {
    override suspend fun getHouse(name: String): Result<House> {
        return withContext(Dispatchers.IO) {
            val house = houses.find { it.name == name }
            if (house == null) {
                Result.Error(IllegalArgumentException("Unable to find house"))
            } else {
                Result.Success(house)
            }
        }
    }

    override suspend fun getHouses(): Result<List<House>> {
        return Result.Success(houses)
    }

}