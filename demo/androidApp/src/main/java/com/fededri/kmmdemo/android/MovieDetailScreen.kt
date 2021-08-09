package com.fededri.kmmdemo.android

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.fededri.kmmdemo.models.Movie

@Composable
fun MovieDetailScreen(movie: Movie, onBack: () -> Unit) {
    BackHandler {
        onBack()
    }
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Image(
            contentScale = ContentScale.Crop,
            painter = rememberImagePainter(
                data = movie.posterPath.orEmpty(),
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)

        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = movie.title,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = movie.overview,
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth(),
            fontStyle = FontStyle.Italic
            )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = "Popularity:",
                fontWeight = FontWeight.Bold
            )

            Text(
                text = movie.popularity.toString(),
            )
        }
    }

}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun PreviewMoviewDetailScreen() {
    MovieDetailScreen(movie = Movie(), {})
}