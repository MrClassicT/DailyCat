package com.android.dailycat.ui.screens.appScreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.dailycat.model.CatPost
import com.android.dailycat.ui.screens.components.CatPostFrame


import com.android.dailycat.ui.screens.loader.Loader

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Feed(
    catPosts: List<CatPost>,
    loadMore: () -> Unit = {}
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        catPosts.size
    }

    if (catPosts.isNotEmpty()) {
        VerticalPager(
            state = pagerState, // The pager state to control the pager
            modifier = Modifier.padding(10.dp), // Fill the max size of the screen
        ) { page ->
            // Our page content
            CatPostFrame(
                catImage = catPosts[page].image,
                catQuote = catPosts[page].quote,
                isFavorite = catPosts[page].favorite
            )
            if (page == catPosts.size - 3) loadMore()
        }
    } else {
        Loader()
    }
}