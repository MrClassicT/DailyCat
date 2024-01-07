package com.android.dailycat.ui.screens.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.android.dailycat.R
import com.android.dailycat.model.CatPost
import java.io.ByteArrayOutputStream


/**
 * Composable that displays a frame for a cat post, including the image, quote, and a favorite icon.
 *
 * @param catPost The cat post to display.
 * @param onFavoriteClick The action to perform when the favorite icon is clicked.
 */
@Composable
fun CatPostFrame(
    catPost: CatPost,
    onFavoriteClick: () -> Unit = {}
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val isLandscape = screenWidth > screenHeight

    if (!isLandscape) { // Portrait mode.
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .zIndex(.1f), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Post(catPost = catPost)
        }
    } else { // Landscape mode.
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .zIndex(.1f)
        ) {
            Post(catPost = catPost, smallImg = true)
        }
    }

    // The star icon
    Column(
        Modifier
            .fillMaxWidth()
            .zIndex(5f),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(modifier=Modifier.testTag("Favorite") ,onClick = onFavoriteClick) {
            if (catPost.favorite) {
                Icon(Icons.Default.Star, contentDescription = "Remove from favorites")
            } else {
                Icon(Icons.Default.StarBorder, contentDescription = "Add to favorites")
            }
        }
    }


}

/**
 * Internal composable that displays the content of a cat post.
 *
 * @param catPost The cat post whose content is to be displayed.
 * @param smallImg Boolean flag to determine if the image should be displayed smaller (for landscape mode).
 */
@Composable
private fun Post(catPost: CatPost, smallImg: Boolean = false) {
    CatImage(catPost.image, smallImg)

    Text(
        modifier = Modifier.padding(
            start = 10.dp,
            end = 10.dp,
            top = if (smallImg) 0.dp else 20.dp
        ),
        text = if (smallImg && catPost.quote.length > 380) catPost.quote.substring(
            0,
            380
        ) + "..." else catPost.quote,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
    )
}

/**
 * Converts a byte array to a Bitmap.
 *
 * @param imageData The byte array to convert.
 * @return The resulting Bitmap.
 */
fun convertImageByteArrayToBitmap(imageData: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
}

/**
 * Composable that displays an image from a byte array.
 *
 * @param catImage The byte array containing the image data.
 * @param isSmall Boolean flag to determine if the image should be displayed smaller.
 */
@Composable
fun CatImage(catImage: ByteArray, isSmall: Boolean = false) {
    var mod: Modifier = Modifier
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val maxHeight = screenHeight.times(.4f)

    if (isSmall) {
        mod = mod
            .fillMaxHeight(.7f)
            .fillMaxWidth(.5f)
    } else mod = mod.heightIn(max = maxHeight)

    Column(
        modifier = mod,
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

/**
 * Preview for the CatPostFrame in portrait orientation.
 */
@Preview(showBackground = true, widthDp = 300, heightDp = 600)
@Composable
fun Portrait_CatPostPreview() {

    getIconByteArray(LocalContext.current)?.let {
        CatPostFrame(
            CatPost(
                image = it, // Template image.
                quote = "Meow! Time spent with cats is never wasted."
            )
        )
    }
}

/**
 * Preview for the CatPostFrame in landscape orientation.
 */
@Preview(showBackground = true, widthDp = 600, heightDp = 300)
@Composable
fun Landscape_CatPostPreview() {

    getIconByteArray(LocalContext.current)?.let {
        CatPostFrame(
            CatPost(
                image = it, // Template image.
                quote = "Meow! Time spent with cats is never wasted."
            )
        )
    }
}

/**
 * Retrieves a drawable as a byte array. Used to render previews.
 *
 * @param context The context to access resources.
 * @return The byte array of the drawable.
 */
fun getIconByteArray(context: Context): ByteArray? {
    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.dailycaticon)

    ByteArrayOutputStream().apply {
        // Compress the bitmap into a JPEG (or PNG) with 100% quality
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, this)
        return toByteArray()
    }
}