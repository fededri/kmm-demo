package com.fededri.kmmdemo.android

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.fededri.kmmdemo.models.Movie
import com.fededri.kmmdemo.movies.MoviesState
import com.fededri.kmmdemo.movies.MoviesViewModel

@Composable
fun MovieDetailScreen(movie: Movie, onBack: () -> Unit) {
    BackHandler {
        onBack()
    }
    Column(Modifier.fillMaxSize()) {
        Image(
            painter = rememberImagePainter(data = movie.posterPath.orEmpty(),
                builder = {
                    scale(Scale.FIT)
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 4.dp))
    }

}

@Preview
@Composable
fun PreviewMoviewDetailScreen() {
    MovieDetailScreen(movie = Movie(), {})
}