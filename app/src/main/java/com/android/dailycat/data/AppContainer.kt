package com.android.dailycat.data

import android.content.Context

interface AppContainer{
    // val Repository : Repository
}


class AppDataContainer(private val context: Context) : AppContainer {

    private val BASE_URL = "" // API URL

    /*
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        private val retrofitService :DailyCatApiService by lazy {
            retrofit.create(DailyCatApiService::class.java)
        }

        override val repoRepository: Repository by lazy {
            ApiRepository(retrofitService)
        }
    */
}