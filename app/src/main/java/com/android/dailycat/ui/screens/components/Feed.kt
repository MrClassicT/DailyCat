package com.android.dailycat.ui.screens.appScreen.components


import android.util.Log
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
        VerticalPager(
            state = pagerState, // The pager state to control the pager
            modifier = Modifier.padding(10.dp), // Fill the max size of the screen
        ) { page ->
            val vm: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)
            // Our page content
            CatPostFrame(
                catImage = catPosts[page].image,
                catQuote = catPosts[page].quote,
                isFavorite = catPosts[page].favorite,
                onFavoriteClick = { shouldBeFavorite ->
                    Log.d(
                        "FavoriteButton",
                        "Request to " + if (shouldBeFavorite) {
                            "remove "
                        } else {
                            "add "
                        } + "post with quote:\r\n" + catPosts[page].quote
                    )
                    if (shouldBeFavorite) {
                        catPosts[page].favorite = true
                        vm.addToFavorites(catPosts[page])
                    } else
                        catPosts[page].favorite = false
                    vm.removeFromFavorites(catPosts[page])
                }
            )
            if (page == catPosts.size - 3) loadMore()
        }
    } else {
        Loader()
    }
}