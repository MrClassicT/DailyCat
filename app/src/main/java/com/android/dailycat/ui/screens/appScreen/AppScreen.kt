package com.android.dailycat.ui.screens.appScreen

//  https://developer.android.com/reference/kotlin/androidx/compose/material/icons/package-summary
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.dailycat.model.TabItem
import com.android.dailycat.ui.AppViewModelProvider
import com.android.dailycat.ui.screens.AppViewModel


/**
 * Composable function that creates the main screen of the application with tab navigation.
 *
 * This function displays a tabbed interface, allowing the user to navigate between different screens.
 * It uses [HorizontalPager] for the page content and [TabRow] for the tab navigation.
 *
 * @param tabItems The list of [TabItem] objects representing each tab in the application.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppScreen(tabItems: List<TabItem>) {


    val vm: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState by vm.uiState.collectAsState()

    val selectedTabIndex = uiState.tabIndex

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
            vm.setTabIndex(pagerState.currentPage)
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
                    onClick = { vm.setTabIndex(index) },
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
            tabItems[index].goToPage()
        }
    }
}

/**
 * Preview function for the main navigation screen.
 *
 * Provides a preview of the main screen inside the IDE, showcasing the layout with no content.
 */
@Preview(showBackground = true, backgroundColor = 0xffff)
@Composable
fun NavigationPreview() {
    AppScreen(listOf())
}