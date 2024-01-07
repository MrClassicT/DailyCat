package com.android.dailycat.ui.screens.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.zIndex
import com.android.dailycat.R
import java.io.ByteArrayOutputStream


@Composable
fun CatPostFrame(
    catImage: ByteArray,
    catQuote: String,
    isFavorite: Boolean = false,
    onFavoriteClick: (Boolean) -> Unit = {}
) {


    Column(
        Modifier
            .fillMaxWidth()
            .zIndex(.1f), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CatImage(catImage)

        Text(
            text = catQuote, fontSize = 18.sp, textAlign = TextAlign.Center
        )
    }

    Column(
        Modifier
            .fillMaxWidth()
            .zIndex(5f),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = {
            onFavoriteClick(!isFavorite)
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

    getIconByteArray(LocalContext.current)?.let {
        CatPostFrame(
            catImage = it, // Template image.
            catQuote = "Meow! Time spent with cats is never wasted.",
        )
    }
}


fun getIconByteArray(context: Context): ByteArray? {
    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.dailycaticon)

    ByteArrayOutputStream().apply {
        // Compress the bitmap into a JPEG (or PNG) with 100% quality
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, this)
        return toByteArray()
    }
}