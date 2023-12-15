package com.android.dailycat.ui.screens.navigation

//  https://developer.android.com/reference/kotlin/androidx/compose/material/icons/package-summary
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController


val tabItems = listOf(
    TabItem(
        title = NavigationEnums.DISCOVER.name,
        unSelectedIcon = Icons.Outlined.NearMe,
        selectedIcon = Icons.Filled.NearMe
    ),
    TabItem(
        title = NavigationEnums.FAVORITES.name,
        unSelectedIcon = Icons.Outlined.Grade,
        selectedIcon = Icons.Filled.Star
    )
)


data class TabItem(
    val title: String,
    val unSelectedIcon: ImageVector,
    val selectedIcon: ImageVector
)


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Navigation(/*navToDiscover: () -> Unit, navToFavorites: () -> Unit*/ navController:NavController) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState {
        tabItems.size
    }

    // Animate the scrolling effect to the desired page when clicking on the icons.
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    // Making sure the selectedtabindex (top navigation icons) are also working when we use scroll navigation.
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }


    // Navigation part on top of the screen. Dynamically generated.
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, tabItem ->
                Tab(selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = tabItem.title) },
                    icon = {
                        Icon(
                            imageVector =
                            if (index == selectedTabIndex) {
                                tabItem.selectedIcon
                            } else
                                tabItem.unSelectedIcon, contentDescription = tabItem.title
                        )
                    }
                )
            }

        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { index ->

//                if(index == 0) navToDiscover()
//                if (index == 1) navToFavorites()
            if (navController.currentBackStackEntry != null) {
                navController.navigate(tabItems[index].title)
            }

        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xffff)
@Composable
fun NavigationPreview() {
    Navigation(navController = NavController(LocalContext.current))
}