package com.android.dailycat.data.repositories

import com.android.dailycat.network.ImgApiService
import com.android.dailycat.network.NetworkModule


class ImgRepository(private val imgApiService: ImgApiService = NetworkModule.catRetrofit.create(ImgApiService::class.java)) {

    suspend fun getCatImage(): ByteArray {
        // Call the API and return the image URL
        val response = imgApiService.getCatImage().body() ?: throw Exception("Failed to load image")

        return response.byteStream().readBytes()
    }
}
