package com.android.dailycat.network

import retrofit2.Response
import retrofit2.http.GET

interface ImgApiService {
    @GET("/cat")
    suspend fun getCatImage(): Response<String>
}
