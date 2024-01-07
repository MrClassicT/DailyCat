package com.android.dailycat.data.favorites

import com.android.dailycat.model.CatPost
import kotlinx.coroutines.flow.Flow

/**
 * Interface for the repository handling favorite cat posts.
 *
 * This interface defines the contract for a repository that manages favorite cat posts. It provides methods to
 * retrieve all favorites as a stream, insert a new favorite post, and delete an existing favorite post.
 */
interface FavoriteRepository {

    /**
     * Retrieves a stream of all favorite cat posts.
     *
     * This method returns a [Flow] emitting a list of [CatPost] objects. The Flow stream will emit new lists
     * whenever there are changes to the favorite cat posts in the repository.
     *
     * @return [Flow]<[List]<[CatPost]>> A flow stream of favorite cat posts.
     */
    fun getAllFavoritePostStream(): Flow<List<CatPost>>

    /**
     * Inserts a new cat post into favorites.
     *
     * This method takes a [CatPost] object and stores it in the repository as a favorite post.
     *
     * @param post The [CatPost] to be marked as favorite and inserted into the repository.
     */
    suspend fun insertFavoritePost(post: CatPost)

    /**
     * Deletes a cat post from favorites.
     *
     * This method removes the specified [CatPost] from the list of favorite posts in the repository.
     *
     * @param post The [CatPost] to be removed from favorites.
     */
    suspend fun deleteFavoritePost(post: CatPost)
}