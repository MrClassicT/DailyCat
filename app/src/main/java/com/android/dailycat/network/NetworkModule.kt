package com.android.dailycat.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val CAT_BASE_URL = "https://cataas.com"
    private const val QUOTE_BASE_URL = "https://meowfacts.herokuapp.com"

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        // Add any interceptors here
        .build()

    val catRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(CAT_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val quoteRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(QUOTE_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}


