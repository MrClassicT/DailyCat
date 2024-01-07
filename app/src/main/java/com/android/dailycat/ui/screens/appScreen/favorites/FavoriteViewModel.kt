package com.android.dailycat.ui.screens.appScreen.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dailycat.data.favorites.FavoriteRepository
import com.android.dailycat.model.CatPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Data class representing the UI state for the favorite posts screen.
 *
 * @property favoritePosts The list of favorite cat posts.
 */
data class FavoritePostsUiState(
    val favoritePosts: List<CatPost> = listOf()
)

/**
 * ViewModel for managing the favorite cat posts.
 *
 * This ViewModel is responsible for handling the business logic related to favorite cat posts. It interacts with
 * the [FavoriteRepository] to perform operations like fetching, adding, and removing favorite posts. The UI state is
 * exposed as a StateFlow for observing changes in the UI.
 *
 * @property favoriteRepository The repository responsible for fetching and updating favorite cat posts.
 */
class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoritePostsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchFavorites()
    }

    /**
     * Fetches favorite cat posts and updates the UI state.
     */
    private fun fetchFavorites() {
        viewModelScope.launch {
            favoriteRepository.getAllFavoritePostStream().collect() {
                _uiState.value = FavoritePostsUiState(favoritePosts = it)
            }
        }
    }

    /**
     * Adds a cat post to the list of favorites.
     *
     * @param post The cat post to add to favorites.
     */
    private fun addToFavorites(post: CatPost) {
        Log.d("addToFavorites", "AddToFavorites in VM called!")
        viewModelScope.launch {
            favoriteRepository.insertFavoritePost(post)
        }
    }

    /**
     * Removes a cat post from the list of favorites.
     *
     * @param post The cat post to remove from favorites.
     */
    private fun removeFromFavorites(post: CatPost) {
        viewModelScope.launch {
            Log.d("ToDelete", post.id.toString() + "\r\n" + post.quote)
            favoriteRepository.deleteFavoritePost(post)
        }
    }


    /**
     * Toggles the favorite status of a cat post.
     *
     * This method checks if the cat post is already in favorites. If it is, it removes it; otherwise, it adds it.
     * It then fetches the updated list of favorite posts.
     *
     * @param catPost The cat post to toggle in favorites.
     */
    fun toggleFavorite(catPost: CatPost) {
        Log.d("toggleFavorite", "In vm - toggleFav")
        try {

            val currentState =
                _uiState.value.favoritePosts.any {
                    it == catPost
                }

            val updatedPost = catPost.copy(favorite = !currentState)

            if (updatedPost.favorite) {
                addToFavorites(updatedPost)
            } else {
                removeFromFavorites(catPost)
            }

            // Refresh the list of favorite posts.
            fetchFavorites()
        } catch (e: Exception) {
            Log.e(
                "toggleFavorite",
                "An error occured while trying to toggleFavorite on the post:\r\n" + e.message.toString()
            )
        }
    }
}