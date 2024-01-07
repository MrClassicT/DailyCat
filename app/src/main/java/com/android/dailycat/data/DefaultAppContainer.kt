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

/**
 * Default implementation of the AppContainer interface.
 *
 * This class is responsible for providing concrete instances of repositories and other dependencies needed throughout
 * the application. It initializes these dependencies lazily to optimize resource usage and performance.
 *
 * @param context The Android context used for initializing certain components, particularly those requiring context,
 *                such as databases.
 */
class DefaultAppContainer(context: Context) : AppContainer {

    /**
     * Provides a lazy-initialized instance of [ImgRepository].
     *
     * This repository handles fetching cat images using a network service.
     */
    override val imgRepository: ImgRepository by lazy {
        ImgRepository(NetworkModule.catRetrofit.create(ImgApiService::class.java))
    }

    /**
     * Provides a lazy-initialized instance of [QuoteRepository].
     *
     * This repository handles fetching quotes using a network service.
     */
    override val quoteRepository: QuoteRepository by lazy {
        QuoteRepository(NetworkModule.quoteRetrofit.create(QuoteApiService::class.java))
    }

    /**
     * Provides a lazy-initialized instance of [CatPostRepository].
     *
     * This repository combines the functionality of [imgRepository] and [quoteRepository]
     * to create cat posts.
     */
    override val catPostRepository: CatPostRepository by lazy {
        CatPostRepository(imgRepository, quoteRepository)
    }


    /**
     * Provides a lazy-initialized instance of [FavoriteRepository].
     *
     * This repository handles the local storage of favorite cat posts using a Room database.
     */
    override val favoriteRepository: FavoriteRepository by lazy {
        OfflineFavoriteRepository(FavoriteDatabase.getDatabase(context).favoriteDao())
    }

}
