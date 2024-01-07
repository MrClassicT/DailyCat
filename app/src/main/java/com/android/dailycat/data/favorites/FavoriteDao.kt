package com.android.dailycat.data.favorites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.dailycat.model.CatPost
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites") // TODO - Check name
    fun getAllFavorites(): Flow<List<CatPost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: CatPost)

    @Delete
    suspend fun delete(post: CatPost)
}