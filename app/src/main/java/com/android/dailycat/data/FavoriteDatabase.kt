package com.android.dailycat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.dailycat.data.FavoriteDatabase.Companion.Instance
import com.android.dailycat.data.favorites.FavoriteDao
import com.android.dailycat.model.CatPost

/**
 * Room database for storing favorite cat posts.
 *
 * This abstract class extends [RoomDatabase] and provides the database instance for the application.
 * It includes the [CatPost] entity and a DAO [FavoriteDao] for accessing the database. The class uses
 * a singleton pattern to ensure a single instance of the database is created.
 *
 * @property Instance A single instance of [FavoriteDatabase]. It's marked as volatile to ensure atomic access
 *                    to the variable across threads.
 */
@Database(entities = [CatPost::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {

    /**
     * Provides the [FavoriteDao] for accessing the favorite cat posts in the database.
     *
     * @return [FavoriteDao] The DAO for accessing and manipulating favorite cat posts.
     */
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        /**
         * Volatile instance to ensure atomic access to [FavoriteDatabase] instance.
         */
        @Volatile
        private var Instance: FavoriteDatabase? = null

        /**
         * Gets or creates the singleton instance of [FavoriteDatabase].
         *
         * If an instance already exists, it returns that instance. If not, it creates a new instance
         * in a thread-safe manner using the Room database builder. It also configures the database
         * to fall back to destructive migration if necessary.
         *
         * @param context The application context.
         * @return [FavoriteDatabase] The single instance of the database.
         */
        fun getDatabase(context: Context): FavoriteDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FavoriteDatabase::class.java, "favorite_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}