package com.ttdrp.gameofthrones

import android.app.Application
import com.ttdrp.gameofthrones.data.AppContainer
import com.ttdrp.gameofthrones.data.AppContainerImpl

class IceAndFireApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}