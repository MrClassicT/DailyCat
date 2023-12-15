package com.android.dailycat

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.dailycat.ui.screens.navigation.NavGraph
import com.android.dailycat.ui.screens.navigation.Navigation

@Composable
fun DailyCatApp(navController: NavHostController? = null) {
    var navCont: NavHostController = rememberNavController()
    if (navController != null) {
        navCont = navController
    }
    Scaffold(
        topBar = {
//            Navigation({ navCont.navigate(NavigationEnums.DISCOVER.name) },
//                { navCont.navigate(NavigationEnums.FAVORITES.name) })
            Navigation(navController = navCont)
        }
    )
    { innerPadding ->

        NavGraph(navController = navCont, innerPadding)

    }
}