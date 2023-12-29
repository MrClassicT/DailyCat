package com.android.dailycat.data.repositories

import com.android.dailycat.network.ImgApiService



class ImgRepository(private val imgApiService: ImgApiService) {

    suspend fun getCatImage(): String {
        // Call the API and return the image URL
        val response = imgApiService.getCatImage()
        if (response.isSuccessful) {
            return response.body()?.string() ?: throw Exception("Failed to load image")
        } else {
            throw Exception("API call failed with error: ${response.errorBody()?.string()}")
        }
    }
}
