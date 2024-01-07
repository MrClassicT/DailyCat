package com.android.dailycat.data.favorites

import com.android.dailycat.model.CatPost
import kotlinx.coroutines.flow.Flow

class OfflineFavoriteRepository(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override fun getAllFavoritePostStream(): Flow<List<CatPost>> = favoriteDao.getAllFavorites()

    override suspend fun insertFavoritePost(post: CatPost) = favoriteDao.insert(post)

    override suspend fun deleteFavoritePost(post: CatPost) =
        favoriteDao.delete(post.quote, post.image)


}