package com.android.dailycat.data.favorites

import com.android.dailycat.model.CatPost
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getAllFavoritePostStream(): Flow<List<CatPost>>

    suspend fun insertFavoritePost(post: CatPost)

    suspend fun deleteFavoritePost(post: CatPost)
}