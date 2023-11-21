package com.android.dailycat.ui.screens.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.dailycat.ui.AppViewModelProvider
import com.android.dailycat.ui.screens.AppViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavGraph(
    appViewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController,
    innerPadding: PaddingValues
){
    NavHost(navController = navController, startDestination = NavigationEnums.DISCOVER.name) {


    }




    }