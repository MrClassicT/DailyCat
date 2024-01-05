package com.android.dailycat.data.repositories

import com.android.dailycat.network.NetworkModule
import com.android.dailycat.network.QuoteApiService

class QuoteRepository(private val quoteApiService: QuoteApiService = NetworkModule.quoteRetrofit.create(QuoteApiService::class.java)) {

    suspend fun getQuote(): String {
        val response = quoteApiService.getQuote()
        if (response.isSuccessful) {
            // Assuming your API returns a JSON object with a "data" field that is an array of strings
            return response.body()?.data?.firstOrNull() ?: throw Exception("No quote found")
        } else {
            // It's generally a good idea to include the status code in the error message
            throw Exception("API call failed with status code ${response.code()} and error: ${response.errorBody()?.string()}")
        }
    }
}
