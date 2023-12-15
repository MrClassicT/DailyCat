package com.android.dailycat

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.dailycat.ui.screens.navigation.NavGraph

@Composable
fun DailyCatApp(navController : NavHostController? = null) {
    var navCont : NavHostController = rememberNavController()
    if (navController != null) {
        navCont = navController
    }
    Scaffold()
    { innerPadding ->

        NavGraph(navController = navCont, innerPadding)

    }
}