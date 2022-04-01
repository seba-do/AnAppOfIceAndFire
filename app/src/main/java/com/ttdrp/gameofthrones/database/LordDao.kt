package com.ttdrp.gameofthrones.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ttdrp.gameofthrones.data.lord.LordResponse
import com.ttdrp.gameofthrones.model.Lord

@Dao
interface LordDao {

    @Query("select * from LordResponse where url is (:url)")
    suspend fun getLord(url: String): LordResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLord(lord: LordResponse)
}