package com.android.dailycat.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.dailycat.R
import java.io.ByteArrayOutputStream


@Composable
fun CatPost(catImage: ByteArray, catQuote: String, isFavorite: Boolean = false) {

    Column(
        Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CatImage(catImage)

        Text(
            text = catQuote, fontSize = 18.sp, textAlign = TextAlign.Center, modifier = Modifier
        )
    }


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { //TODO - Implement adding/removing from favorites.
            Log.d("FavoriteButton", "Clicked! Has yet to be implemented.")
        }) {
            if (isFavorite) Icon(
                Icons.Default.Star, contentDescription = "Remove from favorites"
            )
            else Icon(Icons.Default.StarBorder, contentDescription = "Add to favorites")
        }
    }

}

fun convertImageByteArrayToBitmap(imageData: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
}

@Composable
fun CatImage(catImage: ByteArray) {
    Column(
        modifier = Modifier.padding(10.dp, 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val imgBitMap = convertImageByteArrayToBitmap(catImage)

        Image(
            bitmap = imgBitMap.asImageBitmap(),
            contentDescription = "meowtastic image",
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CatPostPreview() {
    fun getIconByteArray(context: Context): ByteArray? {
        // Replace 'R.drawable.my_icon' with your image resource
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.dailycaticon)

        ByteArrayOutputStream().apply {
            // Compress the bitmap into a JPEG (or PNG) with 100% quality
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, this)
            return toByteArray()
        }
    }



    getIconByteArray(LocalContext.current)?.let {
        CatPost(
            catImage = it,// Template image.
            catQuote = "Meow! Time spent with cats is never wasted."
        )
    }
}