package com.android.dailycat.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.dailycat.model.TabItem
import com.android.dailycat.ui.screens.appScreen.discover.DiscoverScreen
import com.android.dailycat.ui.screens.appScreen.favorites.FavoritesScreen
import com.android.dailycat.ui.screens.navigation.NavigationEnums
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

class AppViewModel(/*private val loadSlipsRepository: LoadSlipsRepository*/) : ViewModel() {


    private val _uiState = MutableStateFlow(AppState())
    val uiState: StateFlow<AppState> = _uiState.asStateFlow()

    var dailyCatApiState: DailyCatApiState by mutableStateOf(DailyCatApiState.Loading)
        private set

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


