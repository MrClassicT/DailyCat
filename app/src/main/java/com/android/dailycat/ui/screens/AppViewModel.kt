package com.android.dailycat.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.NearMe
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dailycat.data.repositories.CatPostRepository
import com.android.dailycat.model.CatPost
import com.android.dailycat.model.TabItem
import com.android.dailycat.network.isConnectedToWeb
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
    val onAboutPage: Boolean = false,
    val isOnline: Boolean = false
)

class AppViewModel(
    private val catPostRepository: CatPostRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppState())
    val uiState: StateFlow<AppState> = _uiState.asStateFlow()

    private val _catPosts = MutableStateFlow<List<CatPost>>(emptyList())
    val catPosts: StateFlow<List<CatPost>> = _catPosts

    init {
        getCatPost()
    }

    fun getCatPost() {
        try {
            viewModelScope.launch {
                fetchCatPosts()
            }

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


    fun toggleFavorite(catPost: CatPost) {
        // Create a new list from the current state to avoid mutating the current state directly.
        val updatedList = _catPosts.value.toMutableList()

        // Find the index of the cat post to update.
        val postIndex = updatedList.indexOfFirst { it == catPost }

        if (postIndex != -1) {
            // If the post is found, copy it with the updated favorite status.
            val updatedPost =
                updatedList[postIndex].copy(favorite = !updatedList[postIndex].favorite)

            // Update the list with the new post.
            updatedList[postIndex] = updatedPost

            // Update the state to emit the new list.
            _catPosts.value = updatedList

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

    fun isConnected(context: Context) {
        _uiState.update {
            it.copy(isOnline = isConnectedToWeb(context))
        }
    }


}






