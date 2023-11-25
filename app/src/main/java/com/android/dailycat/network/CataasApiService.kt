package com.android.dailycat.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.http.GET


private const val baseUrl = "https://cataas.com/"

private val retrofit = Retrofit.Builder().addConverterFactory(
    Json.asConverterFactory("application.json".toMediaType())
)
    .baseUrl(baseUrl)
    .build()

object CatImgApi {
    val retrofitService: CatImgApiService by lazy {
        retrofit.create(CatImgApiService::class.java)
    }
}

interface CatImgApiService {

    @GET("image")
    suspend fun getImage(): ResponseBody
}