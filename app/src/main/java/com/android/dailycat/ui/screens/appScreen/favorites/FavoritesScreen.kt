package com.android.dailycat.ui.screens.appScreen.favorites

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.dailycat.ui.AppViewModelProvider
import com.android.dailycat.ui.screens.AppViewModel
import com.android.dailycat.ui.screens.appScreen.components.Feed

@Composable
fun FavoritesScreen(vm: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Log.i("Navigation", "On Favorites screen")
    val catPosts by vm.catPosts.collectAsState()

    Feed(catPosts = catPosts) // Should not need to fetch since we'll be providing all favorite posts!


}

@Preview
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen()
}