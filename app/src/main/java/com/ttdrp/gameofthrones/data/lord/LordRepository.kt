package com.ttdrp.gameofthrones.data.lord

import com.ttdrp.gameofthrones.data.Result
import com.ttdrp.gameofthrones.database.HousesDatabase
import com.ttdrp.gameofthrones.database.toDatabaseModel
import com.ttdrp.gameofthrones.network.ApiService
import javax.inject.Inject

class LordRepository @Inject constructor(
    private val service: ApiService,
    private val database: HousesDatabase
) {

    suspend fun getLord(id: String) = try {
        val lord = database.lordDao().getLord(id.toInt())
            ?: service.getLord(id)
                ?.toDatabaseModel()
                ?.also { database.lordDao().insertLord(it) }
            ?: throw NoSuchElementException("Lord not found")

        Result.Success(lord)
    } catch (e: Exception) {
        Result.Error(e)
    }
}