package com.android.dailycat.ui.screens.appScreen.discover

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
fun DiscoverScreen(vm: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Log.i("Navigation", "On discovery screen.")
    val catPosts by vm.catPosts.collectAsState()

    Feed(catPosts = catPosts) {
        vm.getCatPost()
    }

}

@Preview
@Composable
fun DiscoverScreenPreview() {
    DiscoverScreen()
}