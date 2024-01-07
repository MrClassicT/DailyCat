package com.android.dailycat.data.favorites

import com.android.dailycat.model.CatPost
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of [FavoriteRepository] for managing favorite cat posts in offline mode.
 *
 * This class implements the repository pattern for favorite cat posts, providing concrete logic for database
 * operations using Room. It facilitates the retrieval, insertion, and deletion of favorite cat posts.
 *
 * @param favoriteDao The DAO for accessing the database operations related to favorite cat posts.
 */
class OfflineFavoriteRepository(private val favoriteDao: FavoriteDao) : FavoriteRepository {

    /**
     * Retrieves a stream of all favorite cat posts from the offline database.
     *
     * This method returns a [Flow] emitting a list of [CatPost] objects from the database.
     * The Flow stream will emit new lists whenever there are changes to the favorite cat posts in the database.
     *
     * @return [Flow]<[List]<[CatPost]>> A flow stream of favorite cat posts from the database.
     */
    override fun getAllFavoritePostStream(): Flow<List<CatPost>> = favoriteDao.getAllFavorites()

    /**
     * Inserts a new cat post into favorites in the offline database.
     *
     * This method takes a [CatPost] object and stores it in the database as a favorite post.
     *
     * @param post The [CatPost] to be marked as favorite and inserted into the database.
     */
    override suspend fun insertFavoritePost(post: CatPost) = favoriteDao.insert(post)

    /**
     * Deletes a cat post from favorites in the offline database.
     *
     * This method removes the specified [CatPost], identified by its quote and image, from the list of favorite posts
     * in the database.
     *
     * @param post The [CatPost] to be removed from favorites in the database.
     */
    override suspend fun deleteFavoritePost(post: CatPost) =
        favoriteDao.delete(post.quote, post.image)


}