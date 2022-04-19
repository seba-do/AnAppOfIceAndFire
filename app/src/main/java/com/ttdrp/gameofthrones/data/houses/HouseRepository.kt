package com.ttdrp.gameofthrones.data.houses

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ttdrp.gameofthrones.data.HouseRemoteMediator
import com.ttdrp.gameofthrones.data.Result
import com.ttdrp.gameofthrones.database.HouseDatabase
import com.ttdrp.gameofthrones.database.HousesDatabase
import com.ttdrp.gameofthrones.database.toDatabaseModel
import com.ttdrp.gameofthrones.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HouseRepository @Inject constructor(
    private val service: ApiService,
    private val database: HousesDatabase
) {

    fun getHousesStream(): Flow<PagingData<HouseDatabase>> {
        val pagingSourceFactory = { database.houseDao().getHouses() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = HouseRemoteMediator(
                service,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    suspend fun getHouse(id: String) = try {
        val house = database.houseDao().getHouse(id.toInt())
            ?: service.getHouse(id)
                ?.toDatabaseModel()
                ?.also { database.houseDao().insertAll(listOf(it)) }
            ?: throw NoSuchElementException("House not found")

        Result.Success(house)
    } catch (e: Exception) {
        Result.Error(e)
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}