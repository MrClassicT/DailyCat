package com.android.dailycat.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
//  https://developer.android.com/reference/kotlin/androidx/compose/material/icons/package-summary
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview


val tabItems = listOf(
    TabItem(
        title = "Discover",
        unSelectedIcon = Icons.Outlined.NearMe,
        selectedIcon = Icons.Filled.NearMe
    ),
    TabItem(
        title = "Favorites",
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
fun Navigation(){

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState {
        tabItems.size
    }


    LaunchedEffect(selectedTabIndex){
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress){
        if(!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }


    Column(modifier = Modifier
        .fillMaxSize()
        ) {
        TabRow(selectedTabIndex = selectedTabIndex ) {
            tabItems.forEachIndexed { index, tabItem ->
                Tab(selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text= tabItem.title) },
                    icon = {
                        Icon(imageVector =
                        if (index == selectedTabIndex){
                            tabItem.selectedIcon
                        }else
                            tabItem.unSelectedIcon
                            , contentDescription = tabItem.title
                        )
                    }
                ) }
            
        }

        HorizontalPager(state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
                index -> 
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                /* TODO: Add page content. */
                Text(text = tabItems[index].title)
            }
        }
    }


}


@Preview(showBackground = true,backgroundColor = 0xffff)
@Composable
fun NavigationPreview(){
    Navigation()
}