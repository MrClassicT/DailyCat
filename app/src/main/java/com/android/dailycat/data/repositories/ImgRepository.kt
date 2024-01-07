package com.android.dailycat.data.repositories

import com.android.dailycat.network.ImgApiService
import com.android.dailycat.network.NetworkModule

/**
 * Repository for fetching cat images.
 *
 * This class provides the logic for interacting with an image API service to fetch cat images.
 * It encapsulates network operations and error handling related to the retrieval of cat images.
 *
 * @property imgApiService The API service used to fetch cat images, defaulting to a created instance
 *                         from [NetworkModule.catRetrofit].
 */
class ImgRepository(private val imgApiService: ImgApiService = NetworkModule.catRetrofit.create(ImgApiService::class.java)) {

    /**
     * Fetches a cat image from the network.
     *
     * This method makes an asynchronous network call to retrieve a cat image. It returns the image
     * as a [ByteArray]. If the network call fails or the response is empty, an exception is thrown.
     *
     * @return [ByteArray] The cat image data as a byte array.
     * @throws Exception if the network call fails or the image can't be retrieved.
     */
    suspend fun getCatImage(): ByteArray {
        // Call the API and return the image URL
        val response = imgApiService.getCatImage().body() ?: throw Exception("Failed to load image")

        return response.byteStream().readBytes()
    }
}
