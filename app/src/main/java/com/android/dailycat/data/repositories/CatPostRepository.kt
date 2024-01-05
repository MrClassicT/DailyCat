package com.android.dailycat.data.repositories

import com.android.dailycat.model.CatPost

class CatPostRepository(
    private val imgRepository: ImgRepository,
    private val quoteRepository: QuoteRepository
) {
    suspend fun getCatPost(): CatPost{

        val image = imgRepository.getCatImage()
        val quote = quoteRepository.getQuote()

        return CatPost(image, quote)
    }


}