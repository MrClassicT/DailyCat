package com.android.dailycat.data.repositories

import com.android.dailycat.network.QuoteApiService

class QuoteRepository(private val quoteApiService: QuoteApiService) {

    suspend fun getQuote(): String {
        val response = quoteApiService.getQuote()
        if (response.isSuccessful) {
            return response.body()?.data?.first() ?: throw Exception("No quote found")
        } else {
            throw Exception("API call failed with error: ${response.errorBody()?.string()}")
        }
    }
}

