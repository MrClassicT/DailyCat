package com.android.dailycat.ui.screens.loader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Composable function that displays a loading message.
 *
 * This function creates a simple loader UI, which includes a message indicating that more content is being loaded.
 * The message is displayed in the center of the screen.
 */
@Composable
fun Loader(){

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Meow! Why don't you have a nap while we get more fluffy friends?!\n" +
                "\n\n" +
                "\nThey'll be here soon!",
            textAlign = TextAlign.Center)
    }

}

/**
 * Preview for the Loader composable.
 *
 * Provides a preview of the Loader composable within the development environment, showcasing the loading message.
 */
@Preview
@Composable
fun LoaderPreview(){
    Loader()
}