package com.ttdrp.gameofthrones.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ttdrp.gameofthrones.data.houses.HouseResponse

@Dao
interface HouseDao {

    @Query("SELECT * FROM Houses")
    fun getHouses(): PagingSource<Int, HouseDatabase>

    @Query("select * from Houses where id is (:id)")
    suspend fun getHouse(id: Int): HouseDatabase?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(houses: List<HouseDatabase>)

    @Query("DELETE FROM Houses")
    suspend fun clearHouses()
}