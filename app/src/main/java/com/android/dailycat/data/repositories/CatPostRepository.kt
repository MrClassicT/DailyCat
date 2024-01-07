package com.android.dailycat.data.repositories

import com.android.dailycat.model.CatPost

class CatPostRepository(
    private val imgRepository: ImgRepository = ImgRepository(),
    private val quoteRepository: QuoteRepository = QuoteRepository(),

    ) {


    suspend fun getCatPost(): CatPost {

        val image = imgRepository.getCatImage()
        val quote = quoteRepository.getQuote()

        return CatPost(image = image, quote = quote)
    }

}