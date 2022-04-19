package com.ttdrp.gameofthrones.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LordDao {

    @Query("select * from Lords where id is (:id)")
    suspend fun getLord(id: Int): LordDatabase?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLord(lord: LordDatabase)
}