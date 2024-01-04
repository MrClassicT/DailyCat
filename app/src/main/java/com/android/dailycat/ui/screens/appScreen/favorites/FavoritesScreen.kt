package com.android.dailycat.ui.screens.appScreen.favorites

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesScreen(){
    Log.i("Navigation","On Favorites screen")
    Row(modifier = Modifier.padding(100.dp)) {

        Text(text = "Favorites")
    }


}

@Preview
@Composable
fun FavoritesScreenPreview(){
    FavoritesScreen()
}