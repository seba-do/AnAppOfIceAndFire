package com.ttdrp.gameofthrones.data

import android.content.Context
import com.ttdrp.gameofthrones.data.houses.IHousesRepository
import com.ttdrp.gameofthrones.data.houses.impl.HousesRepository
import com.ttdrp.gameofthrones.network.Api

interface AppContainer {
    val housesRepository: IHousesRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val housesRepository: IHousesRepository by lazy {
        HousesRepository(
            apiService = Api.retrofitService
        )
    }
}