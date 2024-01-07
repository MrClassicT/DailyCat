package com.android.dailycat.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Object that provides network configurations and Retrofit instances for the application.
 *
 * This object includes the setup of Retrofit clients for different base URLs, specifically for cat images and quotes.
 * It configures OkHttpClient for common settings and adds GsonConverterFactory for JSON serialization and deserialization.
 */
object NetworkModule {
    private const val CAT_BASE_URL = "https://cataas.com"
    private const val QUOTE_BASE_URL = "https://meowfacts.herokuapp.com"

    /**
     * OkHttpClient instance, used for configuring common settings like interceptors.
     */
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        // Add any interceptors here
        .build()

    /**
     * Retrofit instance configured for the cat image API.
     */
    val catRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(CAT_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Retrofit instance configured for the quote API.
     */
    val quoteRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(QUOTE_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}


