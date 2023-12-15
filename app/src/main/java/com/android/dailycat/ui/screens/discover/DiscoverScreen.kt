package com.android.dailycat.ui.screens.discover

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DiscoverScreen(innerPadding: PaddingValues){

Row {

    Text(text = "Discover")
    Log.d("DEBUG","On Discover screen")
}
}


@Preview
@Composable
fun DiscoverScreenPreview(){
    DiscoverScreen(PaddingValues())
}