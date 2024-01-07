package com.android.dailycat.ui.screens.appScreen.favorites

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.dailycat.model.CatPost
import com.android.dailycat.ui.AppViewModelProvider
import com.android.dailycat.ui.screens.appScreen.components.Feed
import com.android.dailycat.ui.screens.components.getIconByteArray

/**
 * Composable function that creates the 'Favorites' screen of the application.
 *
 * This function displays the favorite cat posts stored in the application. It utilizes the [FavoriteViewModel]
 * to observe and display the favorite posts.
 *
 * @param vm The [FavoriteViewModel] instance used to fetch and display favorite posts.
 */
@Composable
fun FavoritesScreen(vm: FavoriteViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Log.i("Navigation", "On Favorites screen")
    val favState by vm.uiState.collectAsState()

    FavoritesScreenListing(modifier = Modifier.testTag("FavPage"), list = favState.favoritePosts)

}

/**
 * Composable function to list favorite cat posts.
 *
 * Displays a list of favorite cat posts using the Feed component or shows a message if there are no favorites.
 *
 * @param list The list of [CatPost] items to display.
 */
@Composable
fun FavoritesScreenListing(modifier: Modifier = Modifier, list: List<CatPost>) {
    if (list.isNotEmpty())
        Feed(catPosts = list) {} // Should not need to fetch since we'll be providing all favorite posts!
    else Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "You don't have any favorites yet!")
    }
}

/**
 * Preview function for displaying the 'No Favorites Yet' message.
 *
 * Provides a preview of the message shown in the 'Favorites' screen when there are no favorites.
 */
@Preview
@Composable
fun NoFavoritesYetScreenPreview() {
    FavoritesScreenListing(list = listOf())
}

/**
 * Preview function for the 'Favorites' screen with sample data.
 *
 * Provides a preview of the 'Favorites' screen with a list of sample favorite cat posts.
 */
@Preview
@Composable
fun FavoritesScreenPreview() {

    getIconByteArray(LocalContext.current)?.let {

        val catImg: ByteArray = it
        val quote1 = "Meow! Time spent with cats is never wasted."
        val quote2 = "Meow! How wonderful, it's naptime!"

        val catList: List<CatPost> = listOf(
            CatPost(image = catImg, quote = quote1, favorite = true),
            CatPost(image = catImg, quote = quote2, favorite = true)
        )
        FavoritesScreenListing(list = catList)

    }

}