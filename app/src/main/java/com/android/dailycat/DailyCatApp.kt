package com.android.dailycat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.dailycat.ui.AppViewModelProvider
import com.android.dailycat.ui.screens.AppViewModel
import com.android.dailycat.ui.screens.navigation.NavGraph
import com.android.dailycat.ui.screens.navigation.NavigationEnums


@Composable
fun DailyCatApp(navController: NavHostController = rememberNavController()) {
    val vm: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState by vm.uiState.collectAsState()

    val goToAbout =
        { navController.navigate(NavigationEnums.ABOUT.name) { launchSingleTop = true } }

    // We must observe the backstack in order to be able to act on changes that happen to it.
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    // Update the ViewModel setOnAbout, based on navigation changes
    LaunchedEffect(currentDestination) {
        vm.setOnAbout(currentDestination?.route == NavigationEnums.ABOUT.name)
    }

    val onAbout: Boolean = uiState.onAboutPage

    Scaffold(
        floatingActionButton = {
            // This FAB is needed to reserve space in Scaffold layout
            // but it will not be shown, hence empty content
        },
        floatingActionButtonPosition = FabPosition.Center, // Set to Center or End to reserve space
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            // Your main content goes here
            NavGraph(navController = navController, innerPadding = innerPadding)

            // Manually position the FAB at the start
            FloatingActionButton(
                onClick = {
                    if (!onAbout) {
                        goToAbout()
                    } else {
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.testTag("About")
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 16.dp) // Add some padding to move it away from the edge

            ) {
                Icon(
                    imageVector = if (!onAbout) Icons.Default.Info else Icons.Default.Close,
                    contentDescription = if (!onAbout) "About" else "Close about page"
                )
            }
        }
    }

}
