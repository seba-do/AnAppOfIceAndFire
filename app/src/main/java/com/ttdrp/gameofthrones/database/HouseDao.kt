package com.ttdrp.gameofthrones.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ttdrp.gameofthrones.data.houses.HouseResponse
import com.ttdrp.gameofthrones.model.House

@Dao
interface HouseDao {

    @Query("SELECT * FROM HouseResponse")
    fun getHouses(): PagingSource<Int, HouseResponse>

    @Query("select * from HouseResponse where name is (:name)")
    suspend fun getHouse(name: String): HouseResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(houses: List<HouseResponse>)

    @Query("SELECT * FROM HouseResponse WHERE url LIKE :query")
    fun pagingSource(query: String): PagingSource<Int, House>

    @Query("DELETE FROM HouseResponse")
    suspend fun clearHouses()
}