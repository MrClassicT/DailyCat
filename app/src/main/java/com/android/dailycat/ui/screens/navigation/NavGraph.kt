package com.android.dailycat.ui.screens.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.dailycat.ui.screens.TabItems
import com.android.dailycat.ui.screens.about.AboutScreen
import com.android.dailycat.ui.screens.appScreen.AppScreen

/**
 * Composable function that sets up the navigation graph for the application.
 *
 * This function uses Jetpack Compose Navigation to set up a navigation graph, defining the screens
 * and their navigation paths within the app. It includes navigation for the main app screen and the about screen.
 *
 * @param innerPadding The padding to apply around the navigation graph.
 * @param navController The [NavHostController] that manages the navigation.
 */
@Composable
fun NavGraph(
    innerPadding: PaddingValues,
    navController: NavHostController,
) {

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