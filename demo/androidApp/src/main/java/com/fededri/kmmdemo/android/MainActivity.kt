package com.fededri.kmmdemo.android

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.fededri.kmmdemo.android.databinding.ContentMainBinding
import com.fededri.kmmdemo.movies.MovieEvents
import com.fededri.kmmdemo.movies.MoviesActions
import com.fededri.kmmdemo.movies.MoviesState
import com.fededri.kmmdemo.movies.MoviesViewModel
import kotlinx.coroutines.flow.collect

val AppColors = lightColors(
    primary = Color(0xffed4f11),
    secondary = Color(0xFFEE6A37)
)

class MainActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel by viewModels { ViewModelFactory(ThreadInfoImpl) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }

    @Composable
    fun App() {
        val state by viewModel.observeState().collectAsState(initial = MoviesState())
        LaunchedEffect(viewModel) {
            Log.i("Movies", "collecting events")
            viewModel.observeEvents().collect { event ->
                processEvent(event)
            }
        }

        MaterialTheme(colors = AppColors) {
            Scaffold(topBar = { Toolbar(state = state) }) {
                AndroidViewBinding(ContentMainBinding::inflate)
            }
        }
    }

    @Composable
    fun Toolbar(state: MoviesState) {
        val isShowingMovieDetail = state.selectedMovie != null
        if (isShowingMovieDetail) {
            MovieDetailTopBar(state = state)
        } else {
            MoviesListTopBar(state = state)
        }
    }

    @Composable
    fun MoviesListTopBar(state: MoviesState) {
        TopAppBar(title = {
            Text(text = "Movies")
        }, actions = {
            IconButton(onClick = { viewModel.action(MoviesActions.RandomizeMoviesList) }) {
                Icon(Icons.Filled.Shuffle, "Shuffle")
            }
        }, navigationIcon = {
            if (state.selectedMovie != null) {
                IconButton(onClick = { findNavController().navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, "Back")
                }
            }
        })
    }

    @Composable
    fun MovieDetailTopBar(state: MoviesState) {
        TopAppBar(title = {
            Text(text = state.selectedMovie?.title.orEmpty())
        }, navigationIcon = {
            IconButton(onClick = {
                viewModel.action(MoviesActions.DeselectMovie)
                findNavController().navigateUp()
            }) {
                Icon(Icons.Filled.ArrowBack, "Back")
            }
        })
    }

    @Composable
    @Preview
    fun AppPreview() {
        App()
    }

    private fun processEvent(event: MovieEvents) {
        when (event) {
            is MovieEvents.OpenSelectedMovie -> findNavController().navigate(R.id.nav_movie_detail)
        }
    }

    private fun findNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}
