package com.ttdrp.gameofthrones.database

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.ttdrp.gameofthrones.data.houses.HouseResponse
import com.ttdrp.gameofthrones.data.lord.LordResponse
import com.ttdrp.gameofthrones.model.House
import com.ttdrp.gameofthrones.model.Lord
import com.ttdrp.gameofthrones.model.RemoteKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(
    entities = [RemoteKeys::class, HouseResponse::class, LordResponse::class],
    version = 2
)
@TypeConverters(RoomConverters::class)
abstract class HousesDatabase : RoomDatabase() {
    abstract fun houseDao(): HouseDao
    abstract fun lordDao(): LordDao
    abstract fun remoteKeysDao(): RemoteKeysDao
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
    ).fallbackToDestructiveMigration().build()
}

object RoomConverters {

    @TypeConverter
    fun jsonToStringList(json: String?) = json.takeIf { !it.isNullOrEmpty() }
        ?.let { Gson().fromJson<List<String>>(it, List::class.java) }
        ?: emptyList()

    @TypeConverter
    fun stringListToJson(list: List<String>?) = list?.let { Gson().toJson(list) }
}
