package com.android.dailycat.ui.screens.appScreen.favorites

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
}