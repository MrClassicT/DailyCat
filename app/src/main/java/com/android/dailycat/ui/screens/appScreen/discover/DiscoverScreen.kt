package com.android.dailycat.ui.screens.appScreen.discover

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.dailycat.ui.AppViewModelProvider
import com.android.dailycat.ui.screens.AppViewModel
import com.android.dailycat.ui.screens.appScreen.components.Feed

@Composable
fun DiscoverScreen(vm: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Log.i("Navigation", "On discovery screen.")
    val context: Context = LocalContext.current
    val uiState by vm.uiState.collectAsState()

    LaunchedEffect(context) {
        vm.isConnected(context)
    }



    if (!uiState.isOnline) {
        OfflineScreen()
    } else {
        val catPosts by vm.catPosts.collectAsState()
        Feed(catPosts = catPosts) { vm.getCatPost() }
    }
}

@Preview
@Composable
fun DiscoverScreenPreview() {
    DiscoverScreen()
}

@Composable
fun OfflineScreen() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No internet connection.\r\nIt looks like your connection is taking a nap!",
            textAlign = TextAlign.Center
        )
    }
}