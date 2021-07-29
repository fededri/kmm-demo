package com.fededri.kmmdemo.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.fededri.kmmdemo.models.MovieListType
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.fededri.kmmdemo.android.views.MovieView
import com.fededri.kmmdemo.movies.MoviesActions
import com.fededri.kmmdemo.movies.MoviesState
import com.fededri.kmmdemo.movies.MoviesViewModel
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity() {

    private val viewModel: MoviesViewModel = MoviesViewModel(ThreadInfoImpl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isFinishing
        setContent {
            MoviesScreen(viewModel.observeState())
        }
        viewModel.action(MoviesActions.FetchMovies(MovieListType.POPULAR))
    }

    @Composable
    fun MoviesScreen(flow: Flow<MoviesState>) {
        val state by flow.collectAsState(initial = MoviesState())

        LazyColumn(modifier = Modifier.fillMaxHeight()) {
           items(state.movies.size, itemContent = { index ->
               val movie = state.movies[index]
               MovieView(movie = movie)
           })
        }
    }
}
