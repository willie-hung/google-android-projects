package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

data class Artwork(
    val imageResId: Int,
    val title: String,
    val artist: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val artworks = listOf(
                        Artwork(R.drawable.starry_night, "Starry Night", "Vincent Van Gogh"),
                        Artwork(R.drawable.vase_with_twelve_sunflowers, "Vase with Twelve Sunflowers", "Vincent Van Gogh"),
                        Artwork(R.drawable.irises, "Irises", "Vincent Van Gogh"),
                        Artwork(R.drawable.olive_trees_with_yellow_sky_and_sun, "Olive Trees with Yellow Sky and Sun", "Vincent Van Gogh"),
                        Artwork(R.drawable.wheat_field_with_crows, "Wheat Field with Crows", "Vincent Van Gogh")
                    )
                    var currentArtIndex by remember { mutableStateOf(0) }
                    val currentArt = artworks[currentArtIndex]

                    ArtSpaceLayout(
                        currentArt,
                        onNextClick = {
                            currentArtIndex = (currentArtIndex + 1) % artworks.size
                        },
                        onPreviousClick = {
                            currentArtIndex = if (currentArtIndex - 1 < 0) artworks.size - 1 else currentArtIndex - 1
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(
    artwork: Artwork,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        ImageView(
            artwork,
            modifier = modifier)
        Spacer(modifier = Modifier.height(16.dp))
        TitleView(
            artwork,
            modifier = modifier)
        Spacer(modifier = Modifier.height(16.dp))
        ButtonView(
            onPreviousClick = onPreviousClick,
            onNextClick = onNextClick,
            modifier = modifier)
    }
}

@Composable
fun ImageView(
    artwork: Artwork,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(artwork.imageResId),
        contentDescription = artwork.title,
        modifier = modifier
            .width(dimensionResource(R.dimen.art_image_width))
            .height(dimensionResource(R.dimen.art_image_height))
    )
}

@Composable
fun TitleView(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    Text(
        text = artwork.title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
    Text(
        text = artwork.artist,
        fontSize = 18.sp,
    )
}

@Composable
fun ButtonView(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row {
        Button(
            onClick = onPreviousClick,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Text("Previous")
        }
        Spacer(modifier = Modifier.width(30.dp))
        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    val dummyArtwork = Artwork(
        imageResId = R.drawable.starry_night,
        title = "Starry Night",
        artist = "Vincent Van Gogh"
    )

    val dummyOnNextClick = { /* Do nothing */ }
    val dummyOnPreviousClick = { /* Do nothing */ }

    ArtSpaceTheme {
        ArtSpaceLayout(
            artwork = dummyArtwork,
            onNextClick = dummyOnNextClick,
            onPreviousClick = dummyOnPreviousClick
        )
    }
}