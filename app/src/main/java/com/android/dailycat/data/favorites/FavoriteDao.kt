package com.android.dailycat.data.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.dailycat.model.CatPost
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for favorite cat posts.
 *
 * This interface defines the database operations for managing favorite cat posts. It includes methods to retrieve all
 * favorites, insert a new favorite, and delete a specific favorite based on its quote and image.
 */
@Dao
interface FavoriteDao {

    /**
     * Retrieves all favorite cat posts from the database.
     *
     * This method returns a Flow list of [CatPost], which represents all the favorite cat posts stored in the database.
     * The Flow stream will emit new values whenever the database favorites table changes.
     *
     * @return [Flow]<[List]<[CatPost]>> A flow list of favorite cat posts.
     */
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<CatPost>>

    /**
     * Inserts a new cat post into the favorites.
     *
     * This method inserts a [CatPost] into the favorites table. If a post with the same primary key already exists,
     * it replaces the existing post due to the [OnConflictStrategy.REPLACE] strategy.
     *
     * @param post The [CatPost] to insert into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: CatPost)

    /**
     * Deletes a specific favorite cat post from the database.
     *
     * This method removes a favorite cat post identified by its quote and image.
     *
     * @param quote The quote of the cat post to be deleted.
     * @param image The image (as a ByteArray) of the cat post to be deleted.
     */
    @Query("DELETE FROM favorites WHERE quote = :quote AND image = :image")
    suspend fun delete(quote: String, image: ByteArray)
}