package com.chris.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chris.artspace.ui.theme.ArtSpaceTheme

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

                   

                    ArtSpaceLayout(modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState()))
                }
            }
        }
    }
}

fun artControlNext (previousArt: Int) : Int {
    var artNumber = previousArt
    return if (artNumber == 5) {
        1
    } else {
        artNumber++

        artNumber
    }
}


fun artControlPrevious (previousArt: Int) : Int {
    var artNumber:Int = previousArt

    return if (artNumber == 1) {
        5
    } else {
        artNumber--

        artNumber
    }
}




@Composable
fun ArtSpaceLayout(modifier: Modifier) {

    var artSpaceArts by remember {
        mutableStateOf(1)
    }

    var previousImage = { artSpaceArts = artControlPrevious(artSpaceArts) }
    var nextImage = { artSpaceArts = artControlNext(artSpaceArts) }

    var imageDisplay = when (artSpaceArts) {
        1 -> R.drawable.image_003
        2 -> R.drawable.image_002
        3 -> R.drawable.image_003
        4 -> R.drawable.image_004
        else -> R.drawable.image_005
    }

    var imageTitle = when (artSpaceArts) {
        1 -> "a walkway lined with red lights leading into the distance"
        2 -> "the sun is setting over the ocean on a cloudy day"
        3 -> "a person walking on a beach next to the ocean"
        4 -> "a red and orange flower in a vase"
        else -> "a view of a body of water with mountains in the background"
    }

    var imageArtist = when (artSpaceArts) {
        1 -> "Andrea de Santis"
        2 -> "Gaspar Zaldo"
        3 -> "Haotian Zheng"
        4 -> "Julia Solonina"
        else -> "Tufis Alexandru"
    }

    var imageYear = when (artSpaceArts) {
        1 -> "2023"
        2 -> "2022"
        3 -> "2023"
        4 -> "2024"
        else -> "2021"
    }


    Column (modifier = modifier,
        verticalArrangement = Arrangement.Center
        ) {

        ArtDisplay(shape = RectangleShape, imageResource = imageDisplay)


        Spacer(modifier = Modifier.height(50.dp))

        ArtDisplayDetail(artName = imageTitle, artist = imageArtist, year = imageYear)

        Spacer(modifier = Modifier.height(20.dp))

        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
            ) {

            ArtSpaceNavigation(name = "Previous", previousImage)
            ArtSpaceNavigation(name = "Next", nextImage)

        }
    }
}

@Composable

fun ArtDisplay (
    shape: Shape,
    @DrawableRes imageResource: Int
) {
    Card(
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = shape,
        modifier = Modifier.padding(top = 40.dp)

    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Art",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(height = 450.dp, width = 450.dp)
        )

    }
}

@Composable

fun ArtDisplayDetail (
    artName: String,
    artist: String,
    year: String) {
    Card(
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(Color.LightGray),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp, bottom = 5.dp).size(width = 500.dp, height = 135.dp),
        shape = RectangleShape
        //elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Text(text = artName, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 15.dp, start = 10.dp) )
        Spacer(modifier = Modifier.height(12.dp))

        Row (modifier = Modifier.padding(bottom = 15.dp, start = 10.dp)) {
            Text(text = artist, fontWeight = FontWeight.Bold )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = year, fontStyle = FontStyle.Italic)
        }
    }

}

@Composable
fun ArtSpaceNavigation (name: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.size(width = 120.dp, height = 35.dp)) {
        Text(text = name )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout(Modifier)
    }
}