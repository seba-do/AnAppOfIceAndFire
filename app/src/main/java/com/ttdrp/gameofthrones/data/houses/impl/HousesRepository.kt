package com.ttdrp.gameofthrones.data.houses.impl

import com.ttdrp.gameofthrones.data.Result
import com.ttdrp.gameofthrones.data.houses.IHousesRepository
import com.ttdrp.gameofthrones.database.HousesDatabase
import com.ttdrp.gameofthrones.database.asDomainModel
import com.ttdrp.gameofthrones.model.House
import com.ttdrp.gameofthrones.model.asDatabaseModel
import com.ttdrp.gameofthrones.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HousesRepository @Inject constructor(
    private val remote: ApiService,
    private val database: HousesDatabase
) : IHousesRepository {

    var houses: MutableStateFlow<List<House>> = MutableStateFlow(emptyList())

    override suspend fun getHouse(name: String): Result<House> = try {
        Result.Success(database.houseDao.getHouse(name))
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun getHouses() = try {
        houses.value = database.houseDao.getHouses().asDomainModel()
        Result.Success(houses.value)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun refreshHouses() = try {
        val houses = remote.getHouses()
        database.houseDao.insertAll(houses.asDatabaseModel())
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
}