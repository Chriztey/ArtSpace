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

                    ArtSpaceLayout(modifier = Modifier)

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




    Column (modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
        ) {

        Card(
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(Color.White),
            shape = RectangleShape,
            modifier = Modifier.padding(top = 40.dp)
            //elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.image_003),
                contentDescription = "Art",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(height = 450.dp, width = 450.dp)
                    //.border(BorderStroke(1.dp, Color.DarkGray))
                    //.shadow(15.dp)
                    //.background(Color.White),

                )

        }

        Spacer(modifier = Modifier.height(50.dp))

        Card(
            elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.cardColors(Color.LightGray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp),
            shape = RectangleShape
            //elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Text(text = "ART NAME $artSpaceArts", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(top = 15.dp, start = 10.dp) )

            Row (modifier = Modifier.padding(bottom = 15.dp, start = 10.dp)) {
                Text(text = "ABC", fontWeight = FontWeight.Bold )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "ABC", fontStyle = FontStyle.Italic)
            }
        }

        //Spacer(modifier = Modifier.height(30.dp))

        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
            ) {

            
            Button(onClick = previousImage, modifier = Modifier.size(width = 120.dp, height = 35.dp)) {
                Text(text = "Previous")
            }
            Button(onClick = nextImage , modifier = Modifier.size(width = 120.dp, height = 35.dp)) {
                Text(text = "Next")
            }
        }

//        Box (
//            Modifier
//                .background(Color.Green)
//                .fillMaxWidth()) {
//
//            Column (modifier = Modifier.padding(10.dp)) {
//
//                Text(text = "ART NAME", style = MaterialTheme.typography.headlineMedium, )
//
//                Row {
//                    Text(text = "ABC", fontWeight = FontWeight.Bold)
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(text = "ABC", fontStyle = FontStyle.Italic)
//                }
//
//            }
//
//        }



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
        //elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Art",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(height = 450.dp, width = 450.dp)
            //.border(BorderStroke(1.dp, Color.DarkGray))
            //.shadow(15.dp)
            //.background(Color.White),
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
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp),
        shape = RectangleShape
        //elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Text(text = artName, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(top = 15.dp, start = 10.dp) )

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