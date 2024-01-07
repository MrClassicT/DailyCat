package com.android.dailycat.data.repositories

import com.android.dailycat.model.CatPost

/**
 * Repository for fetching cat posts.
 *
 * This class provides the logic to create a [CatPost] by combining data from an image repository and a quote repository.
 * It encapsulates the logic of fetching a cat image and a quote to create a cat post.
 *
 * @property imgRepository The repository responsible for fetching cat images.
 * @property quoteRepository The repository responsible for fetching quotes.
 */
class CatPostRepository(
    private val imgRepository: ImgRepository = ImgRepository(),
    private val quoteRepository: QuoteRepository = QuoteRepository(),

    ) {


    /**
     * Fetches a cat post which includes an image and a quote.
     *
     * This method asynchronously fetches a cat image and a quote and then combines them into a [CatPost].
     * The image is fetched from [imgRepository] and the quote from [quoteRepository].
     *
     * @return [CatPost] A cat post containing a cat image and a quote.
     */
    suspend fun getCatPost(): CatPost {

        val image = imgRepository.getCatImage()
        val quote = quoteRepository.getQuote()

        return CatPost(image = image, quote = quote)
    }

}