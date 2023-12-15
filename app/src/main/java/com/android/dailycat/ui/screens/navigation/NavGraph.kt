package com.android.dailycat.ui.screens.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.dailycat.ui.screens.about.AboutScreen
import com.android.dailycat.ui.screens.discover.DiscoverScreen
import com.android.dailycat.ui.screens.favorites.FavoritesScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(navController = navController, startDestination = NavigationEnums.DISCOVER.name) {
        composable(NavigationEnums.ABOUT.name) {
            AboutScreen()
        }
        composable(NavigationEnums.DISCOVER.name) {
            DiscoverScreen(innerPadding)
        }
        composable(NavigationEnums.FAVORITES.name) {
            FavoritesScreen(innerPadding)
        }


    }


}