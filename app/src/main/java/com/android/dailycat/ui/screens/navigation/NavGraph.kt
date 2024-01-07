package com.android.dailycat.ui.screens.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.dailycat.ui.AppViewModelProvider
import com.android.dailycat.ui.screens.TabItems
import com.android.dailycat.ui.screens.about.AboutScreen
import com.android.dailycat.ui.screens.appScreen.AppScreen
import com.android.dailycat.ui.screens.appScreen.favorites.FavoriteViewModel

@Composable
fun NavGraph(
    innerPadding: PaddingValues,
    navController: NavHostController,
    favoriteViewModel: FavoriteViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val favoriteUiState by favoriteViewModel.uiState.collectAsState()
    if(favoriteUiState.favoritePosts.isEmpty()) Log.i("Favorites", "No Favorites found!")

    NavHost(
        navController = navController, startDestination = NavigationEnums.APP.DISCOVER.name
    ) {


        composable(NavigationEnums.ABOUT.name) {
            AboutScreen()
        }

        composable(NavigationEnums.APP.DISCOVER.name) {
            AppScreen(TabItems.tabList)
        }

    }


}