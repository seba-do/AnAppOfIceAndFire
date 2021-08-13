package com.ttdrp.gameofthrones.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.gson.Gson
import com.ttdrp.gameofthrones.model.House
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

@Dao
interface HouseDao {

    @Query("select * from databasehouse")
    suspend fun getHouses(): List<DatabaseHouse>

    @Query("select * from databasehouse where name is (:name)")
    suspend fun getHouse(name: String): House

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(houses: List<DatabaseHouse>)
}

@Database(entities = [DatabaseHouse::class], version = 1)
@TypeConverters(RoomConverters::class)
abstract class HousesDatabase : RoomDatabase() {
    abstract val houseDao: HouseDao
}

@Module
@InstallIn(SingletonComponent::class)
object HousesDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        HousesDatabase::class.java,
        "houses"
    ).build()
}

object RoomConverters {

    @TypeConverter
    fun jsonToStringList(json: String?) = json.takeIf { !it.isNullOrEmpty() }
        ?.let { Gson().fromJson<List<String>>(it, List::class.java) }
        ?: emptyList()

    @TypeConverter
    fun stringListToJson(list: List<String>?) = list?.let { Gson().toJson(list) }
}
