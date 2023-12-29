// File: data/AppContainer.kt
package com.android.dailycat.data

import com.android.dailycat.data.repositories.ImgRepository
import com.android.dailycat.data.repositories.QuoteRepository

interface AppContainer {
    val imgRepository: ImgRepository
    val quoteRepository: QuoteRepository
}
