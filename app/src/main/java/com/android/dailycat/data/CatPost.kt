import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.dailycat.R

@Composable
fun CatPost(catImagePainter: Painter, catQuote: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()
    ) {

        CatImage(catImagePainter)


    Text(
        text = catQuote,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp)

    )
}}


@Composable
fun CatImage(catImagePainter: Painter) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = catImagePainter,
            contentDescription = "meowtastic image",
            contentScale = ContentScale.FillWidth


        )
    }
}

@Preview(showBackground = true)
@Composable
fun CatPostPreview() {
    CatPost(
        catImagePainter = painterResource(id = R.drawable.dailycaticon), // Template image.
        catQuote = "Meow! Time spent with cats is never wasted."
    )
}