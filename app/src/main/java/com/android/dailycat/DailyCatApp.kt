package com.android.dailycat

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.dailycat.model.TabItem
import com.android.dailycat.ui.screens.navigation.NavGraph
import com.android.dailycat.ui.screens.navigation.Navigation
import com.android.dailycat.ui.screens.navigation.NavigationEnums

@Composable
fun DailyCatApp(navController: NavHostController = rememberNavController()) {

    val goToDiscover: () -> Unit = {
        navController.popBackStack(
            NavigationEnums.DISCOVER.name,
            inclusive = false
        )
    }

    val goToFavorites =
        { navController.navigate(NavigationEnums.FAVORITES.name) { launchSingleTop = true } }
    val goToAbout =
        { navController.navigate(NavigationEnums.ABOUT.name) { launchSingleTop = true } }

    val tabItems = listOf(
        TabItem(
            title = NavigationEnums.DISCOVER.name,
            unSelectedIcon = Icons.Outlined.NearMe,
            selectedIcon = Icons.Filled.NearMe,
            onClick = { goToDiscover() }
        ),
        TabItem(
            title = NavigationEnums.FAVORITES.name,
            unSelectedIcon = Icons.Outlined.Grade,
            selectedIcon = Icons.Filled.Star,
            onClick = { goToFavorites() }
        )
    )

    var onAbout by rememberSaveable { mutableStateOf(false || navController.currentDestination.toString() == NavigationEnums.ABOUT.name) }

    Scaffold(
        topBar = {
            if(!onAbout)
            Navigation(tabItems)
        },
        floatingActionButton = {

            FloatingActionButton(onClick = {

                Log.d("FAB", "Clicked!")
                if (onAbout) Log.d("FAB", "onAbout True")
                if (!onAbout) Log.d("FAB", "onAbout False")

                if (!onAbout) goToAbout()
                else navController.popBackStack()

                onAbout = !onAbout
            }) {
                if (!onAbout)
                    Icon(Icons.Default.Info, contentDescription = "About")
                else Icon(Icons.Default.Close, contentDescription = "Close about page")
            }

        }
    )
    { innerPadding ->

        NavGraph(navController = navController, innerPadding = innerPadding)

    }
}