package com.android.dailycat.network

import retrofit2.Response
import retrofit2.http.GET
import java.net.URL

interface ImgApiService {
    @GET("/cat")
    suspend fun getCatImage(): Response<URL>
}
