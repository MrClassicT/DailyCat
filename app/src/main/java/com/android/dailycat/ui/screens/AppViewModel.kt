package com.android.dailycat.ui.screens

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dailycat.data.favorites.FavoriteRepository
import com.android.dailycat.data.repositories.CatPostRepository
import com.android.dailycat.model.CatPost
import com.android.dailycat.model.TabItem
import com.android.dailycat.ui.screens.appScreen.discover.DiscoverScreen
import com.android.dailycat.ui.screens.appScreen.favorites.FavoritesScreen
import com.android.dailycat.ui.screens.navigation.NavigationEnums
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

object TabItems {

    val tabList = listOf(
        TabItem(
            title = NavigationEnums.APP.DISCOVER.name,
            unSelectedIcon = Icons.Outlined.NearMe,
            selectedIcon = Icons.Filled.NearMe,
            goToPage = { DiscoverScreen() }
        ),
        TabItem(
            title = NavigationEnums.APP.FAVORITES.name,
            unSelectedIcon = Icons.Outlined.Grade,
            selectedIcon = Icons.Filled.Star,
            goToPage = { FavoritesScreen() }
        )
    )
}

data class AppState(
    val tabIndex: Int = 0,
    val onAboutPage: Boolean = false
)

class AppViewModel(
    private val catPostRepository: CatPostRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppState())
    val uiState: StateFlow<AppState> = _uiState.asStateFlow()

    private val _catPosts = MutableStateFlow<List<CatPost>>(emptyList())
    val catPosts: StateFlow<List<CatPost>> = _catPosts

    init {
        getCatPost()
    }

    var dailyCatApiState: DailyCatApiState by mutableStateOf(DailyCatApiState.Loading)
        private set


//    val catPost: LiveData<CatPost> = liveData {
//        val post = catPostRepository.getCatPost()
//        emit(post)
//    }

    fun getCatPost() {
        try {
            viewModelScope.launch { fetchCatPosts() }
        } catch (e: Exception) {
            Log.e("GetCatPostError", e.message.toString())

        }
    }

    // Function to asynchronously fetch a list of CatPost items
    private fun fetchCatPosts() {
        viewModelScope.launch(Dispatchers.IO) { // Use Dispatchers.IO for network/database operations
            val posts = mutableListOf<CatPost>()
            for (i in 1..3) { // Fetch 5 posts for example
                try {
                    val post = catPostRepository.getCatPost()
                    posts.add(post)
                    Log.i("FetchCatPosts", "MORE KITTIES!!!")
                } catch (e: Exception) {
                    // Handle exceptions, e.g., by logging or setting an error state
                    Log.e("FetchCatPosts", "Error fetching cat post", e)
                }
            }

            val updatedPosts = _catPosts.value.toMutableList().apply {
                addAll(posts)
            }
            _catPosts.value = updatedPosts
        }
    }


    fun addToFavorites(post: CatPost) {
        viewModelScope.launch {
            favoriteRepository.insertFavoritePost(post)
        }
    }

    fun removeFromFavorites(post: CatPost) {
        viewModelScope.launch {
            Log.d("ToDelete", post.id.toString() + "\r\n" + post.quote)

            favoriteRepository.deleteFavoritePost(post)
        }
    }

    fun setTabIndex(index: Int) {
        _uiState.update {
            it.copy(
                tabIndex = index
            )
        }
    }

    fun setOnAbout(boolean: Boolean) {
        _uiState.update { it.copy(onAboutPage = boolean) }
    }


}


sealed interface DailyCatApiState {
    object Success : DailyCatApiState
    object Error : DailyCatApiState
    object Loading : DailyCatApiState
}





