package com.android.dailycat.ui.screens.appScreen.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dailycat.data.favorites.FavoriteRepository
import com.android.dailycat.model.CatPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class FavoritePostsUiState(
    val favoritePosts: List<CatPost> = listOf()
)

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoritePostsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchFavorites()
    }

    private fun fetchFavorites() {
        viewModelScope.launch {
            favoriteRepository.getAllFavoritePostStream().collect() {
                _uiState.value = FavoritePostsUiState(favoritePosts = it)
            }
        }
    }


    private fun addToFavorites(post: CatPost) {
        Log.d("addToFavorites", "AddToFavorites in VM called!")
        viewModelScope.launch {
            favoriteRepository.insertFavoritePost(post)
        }
    }

    private fun removeFromFavorites(post: CatPost) {
        viewModelScope.launch {
            Log.d("ToDelete", post.id.toString() + "\r\n" + post.quote)
            favoriteRepository.deleteFavoritePost(post)
        }
    }


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