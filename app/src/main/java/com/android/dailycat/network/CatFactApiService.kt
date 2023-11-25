package com.android.dailycat.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET


private const val baseUrl = "https://meowfacts.herokuapp.com/"

private val retrofit = Retrofit.Builder().addConverterFactory(
    Json.asConverterFactory("application.json".toMediaType())
).baseUrl(baseUrl).build()


object CatQuoteApi{
    val retrofitService: CatFactApiService by lazy {
        retrofit.create(CatFactApiService::class.java)
    }
}


interface CatFactApiService {
@GET()
suspend fun getQuote(): String
}