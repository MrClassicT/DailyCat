package com.android.dailycat.data

import android.content.Context
import com.android.dailycat.data.favorites.FavoriteRepository
import com.android.dailycat.data.favorites.OfflineFavoriteRepository
import com.android.dailycat.data.repositories.CatPostRepository
import com.android.dailycat.data.repositories.ImgRepository
import com.android.dailycat.data.repositories.QuoteRepository
import com.android.dailycat.network.ImgApiService
import com.android.dailycat.network.NetworkModule
import com.android.dailycat.network.QuoteApiService

class DefaultAppContainer(context: Context) : AppContainer {
    override val imgRepository: ImgRepository by lazy {
        ImgRepository(NetworkModule.catRetrofit.create(ImgApiService::class.java))
    }

    override val quoteRepository: QuoteRepository by lazy {
        QuoteRepository(NetworkModule.quoteRetrofit.create(QuoteApiService::class.java))
    }

    override val catPostRepository: CatPostRepository by lazy {
        CatPostRepository(imgRepository, quoteRepository)
    }

    override val favoriteRepository : FavoriteRepository by lazy{
        OfflineFavoriteRepository(FavoriteDatabase.getDatabase(context).favoriteDao())
    }

}
