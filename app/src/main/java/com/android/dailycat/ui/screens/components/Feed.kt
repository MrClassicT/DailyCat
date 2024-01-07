package com.android.dailycat.ui.screens.appScreen.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.dailycat.model.CatPost
import com.android.dailycat.ui.AppViewModelProvider
import com.android.dailycat.ui.screens.AppViewModel
import com.android.dailycat.ui.screens.appScreen.favorites.FavoriteViewModel
import com.android.dailycat.ui.screens.components.CatPostFrame
import com.android.dailycat.ui.screens.loader.Loader

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Feed(
    catPosts: List<CatPost>,
    loadMore: () -> Unit = {},
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        catPosts.size
    }

    if (catPosts.isNotEmpty()) {
        val fVm: FavoriteViewModel = viewModel(factory = AppViewModelProvider.Factory)
        val vm: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)

        VerticalPager(
            state = pagerState, // The pager state to control the pager
            modifier = Modifier.padding(10.dp), // Fill the max size of the screen
        ) { page ->
            // Our page content
            CatPostFrame(
                catPosts[page],
                onFavoriteClick = {
                    // Update repo + favorites tab
                    fVm.toggleFavorite(catPosts[page])
                    // Update discover UI
                    vm.toggleFavorite(catPosts[page])
                }
            )
            if (page == catPosts.size - 3) loadMore()
        }
    } else {
        Loader()
    }
}