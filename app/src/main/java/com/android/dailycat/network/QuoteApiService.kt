package com.android.dailycat.network

import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit service interface for fetching quotes.
 *
 * This interface defines the HTTP operations for fetching quotes. It includes a method to make a network
 * call to retrieve a quote. The method is designed to be used with Retrofit to handle API calls asynchronously.
 */
interface QuoteApiService {

    /**
     * Fetches a quote from the server.
     *
     * This method sends a GET request to the specified endpoint to retrieve a quote.
     * It is a suspending function suitable for use in a coroutine context.
     *
     * @return [Response]<[QuoteResponse]> The response from the server, encapsulating the quote data.
     */
    @GET("/")
    suspend fun getQuote(): Response<QuoteResponse>
}

/**
 * Data class representing the response from a quote API.
 *
 * This class encapsulates the quote data returned by the API. It expects a list of strings where
 * each string represents an individual quote.
 *
 * @property data The list of quotes returned by the API.
 */
data class QuoteResponse(val data: List<String>)
