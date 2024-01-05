package com.android.dailycat.ui.screens.appScreen.discover

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.dailycat.ui.AppViewModelProvider
import com.android.dailycat.ui.components.CatPost
import com.android.dailycat.ui.screens.AppViewModel
//import com.google.accompanist.pager.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoverScreen(vm: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    // Assume catPosts is a list of CatPost items
    val catPosts by vm.catPosts.collectAsState()

    // Remember a PagerState
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        catPosts.size
    }

    VerticalPager(
        state = pagerState, // The pager state to control the pager
        modifier = Modifier.padding(10.dp), // Fill the max size of the screen
    ) { page ->
        // Our page content
        if (catPosts.isNotEmpty()) {
            CatPost(catImage = catPosts[page].image, catQuote = catPosts[page].quote)
        } else {
            Text(text = "Loading cats...")
        }
    }
}

@Preview
@Composable
fun DiscoverScreenPreview() {
    DiscoverScreen()
}