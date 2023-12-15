package com.android.dailycat.ui.screens.favorites

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavoritesScreen(innerPadding:PaddingValues){
    Log.d("DEBUG","On Favorites screen")


}

@Preview
@Composable
fun FavoritesScreenPreview(){
    FavoritesScreen(PaddingValues())
}