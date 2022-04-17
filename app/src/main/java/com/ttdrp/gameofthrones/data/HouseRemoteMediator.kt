package com.ttdrp.gameofthrones.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ttdrp.gameofthrones.data.houses.HouseResponse
import com.ttdrp.gameofthrones.database.HousesDatabase
import com.ttdrp.gameofthrones.model.RemoteKeys
import com.ttdrp.gameofthrones.network.ApiService
import retrofit2.HttpException
import java.io.IOException

private const val HOUSES_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class HouseRemoteMediator(
    private val service: ApiService,
    private val housesDatabase: HousesDatabase
) : RemoteMediator<Int, HouseResponse>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HouseResponse>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: HOUSES_STARTING_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = service.getHouses(page)

            val endOfPaginationReached = apiResponse.isEmpty()
            housesDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    housesDatabase.remoteKeysDao().clearRemoteKeys()
                    housesDatabase.houseDao().clearHouses()
                }
                val prevKey = if (page == HOUSES_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = apiResponse.map {
                    RemoteKeys(id = it.url, prevKey = prevKey, nextKey = nextKey)
                }
                housesDatabase.remoteKeysDao().insertAll(keys)
                housesDatabase.houseDao().insertAll(apiResponse)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, HouseResponse>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { house ->
                housesDatabase.remoteKeysDao().remoteKeysHouseId(house.url)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, HouseResponse>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { house ->
                housesDatabase.remoteKeysDao().remoteKeysHouseId(house.url)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, HouseResponse>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { url ->
                housesDatabase.remoteKeysDao().remoteKeysHouseId(url)
            }
        }
    }
}