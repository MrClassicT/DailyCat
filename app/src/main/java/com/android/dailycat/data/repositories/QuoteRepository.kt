package com.android.dailycat.data.repositories

import com.android.dailycat.network.NetworkModule
import com.android.dailycat.network.QuoteApiService

/**
 * Repository for fetching quotes.
 *
 * This class provides the logic for interacting with a quote API service to fetch quotes.
 * It encapsulates network operations and error handling related to the retrieval of quotes.
 *
 * @property quoteApiService The API service used to fetch quotes, defaulting to a created instance
 *                           from [NetworkModule.quoteRetrofit].
 */
class QuoteRepository(private val quoteApiService: QuoteApiService = NetworkModule.quoteRetrofit.create(QuoteApiService::class.java)) {

    /**
     * Fetches a quote from the network.
     *
     * This method makes an asynchronous network call to retrieve a quote. It handles successful and unsuccessful
     * responses by returning the quote as a String, or throwing an exception if no quote is found or if the API call fails.
     *
     * @return [String] The fetched quote.
     * @throws Exception if no quote is found or if the API call fails, including the status code and error details in the exception message.
     */
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
