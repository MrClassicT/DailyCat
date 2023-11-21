package com.android.dailycat

import android.app.Application
import com.android.dailycat.data.AppContainer
import com.android.dailycat.data.AppDataContainer

class DailyCatApplication : Application() {

    lateinit var container : AppContainer

    override fun onCreate(){
        super.onCreate()
        container = AppDataContainer(this)
        }

}
