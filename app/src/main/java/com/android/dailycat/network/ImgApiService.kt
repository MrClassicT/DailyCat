package com.android.dailycat.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit service interface for fetching cat images.
 *
 * This interface defines the HTTP operations for fetching cat images. It includes a method to make a network
 * call to retrieve a cat image. The method is designed to be used with Retrofit to handle API calls asynchronously.
 */
interface ImgApiService {

    /**
     * Fetches a cat image from the server.
     *
     * This method sends a GET request to the specified endpoint to retrieve a cat image.
     * It is a suspending function suitable for use in a coroutine context.
     *
     * @return [Response]<[ResponseBody]> The response from the server, encapsulating the cat image data.
     */
    @GET("/cat")
    suspend fun getCatImage(): Response<ResponseBody>
}
