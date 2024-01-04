package com.android.dailycat.data.repositories

import com.android.dailycat.network.ImgApiService



class ImgRepository(private val imgApiService: ImgApiService) {

    suspend fun getCatImage(): ByteArray {
        // Call the API and return the image URL
        val response = imgApiService.getCatImage().body()?: throw Exception("Failed to load image")
        val imageData = response.readBytes()

    // TODO - add saving option?
    return imageData
    }
}
