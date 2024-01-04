package com.android.dailycat.ui.screens.discover

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DiscoverScreen() {
    Log.i("Navigation","On discover screen.")
    Row(modifier = Modifier.padding(100.dp)) {

        Text(text = "Discover")

    }
}


@Preview
@Composable
fun DiscoverScreenPreview() {
    DiscoverScreen()
}