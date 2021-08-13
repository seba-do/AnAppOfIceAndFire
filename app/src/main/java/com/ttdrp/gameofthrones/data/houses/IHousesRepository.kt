package com.ttdrp.gameofthrones.data.houses

import com.ttdrp.gameofthrones.data.Result
import com.ttdrp.gameofthrones.model.House

interface IHousesRepository {

    /** Get a specific House */
    suspend fun getHouse(name: String) : Result<House>

    /** Get a list with all Houses */
    suspend fun getHouses(): Result<List<House>>

    suspend fun refreshHouses(): Result<Unit>
}