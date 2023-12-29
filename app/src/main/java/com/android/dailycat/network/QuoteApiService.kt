package com.android.dailycat.network

import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiService {
    @GET("/")
    suspend fun getQuote(): Response<QuoteResponse>
}

data class QuoteResponse(val data: List<String>)
