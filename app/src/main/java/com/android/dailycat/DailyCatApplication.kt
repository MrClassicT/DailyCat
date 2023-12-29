package com.android.dailycat

import android.app.Application
import com.android.dailycat.data.DefaultAppContainer

class DailyCatApplication : Application() {

    lateinit var container : DefaultAppContainer

    override fun onCreate(){
        super.onCreate()

        container = DefaultAppContainer(this)

    }

}
