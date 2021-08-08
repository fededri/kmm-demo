package com.fededri.kmmdemo.android

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import com.fededri.kmmdemo.android.views.MovieView
import com.fededri.kmmdemo.models.Movie
import com.fededri.kmmdemo.movies.MoviesActions
import com.fededri.kmmdemo.movies.MoviesState

@Composable
fun MoviesListView(state: MoviesState, onMovieSelected: (Movie) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(state.movies.size, itemContent = { index ->
            val movie = state.movies[index]
            //key(movie.name) {
                MovieView(movie = movie, onMovieClick = onMovieSelected)
            //}
        })
    }
}