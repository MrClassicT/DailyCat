package com.android.dailycat.ui.screens.about
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.dailycat.R.drawable.dailycaticon

/**
 * Composable function that creates the 'About' screen of the application.
 *
 * This function displays the 'About' screen, showing an app icon and text information about the creator.
 * It utilizes Column layout to arrange the elements vertically and align them in the center.
 */
@Composable
fun AboutScreen() {
    Log.i("Navigation","On about screen.")
    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Icon
        Image(
            modifier = Modifier.fillMaxHeight(.7f),
            painter = painterResource(dailycaticon),
            contentDescription = "App Icon",
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Created by",
            textAlign = TextAlign.Center
        )

        Text(
            text = "Tristan Cuvelier",
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Preview function for the 'About' screen.
 *
 * Provides a preview of the 'About' screen inside the IDE, showcasing how it will look in the app.
 */
@Preview(showBackground = true, backgroundColor = 0xffff )
@Composable
fun AboutScreenPreview(){
    AboutScreen()
}