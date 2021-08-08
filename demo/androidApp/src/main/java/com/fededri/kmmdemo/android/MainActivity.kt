package com.fededri.kmmdemo.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.fededri.kmmdemo.models.MovieListType
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.fededri.kmmdemo.android.databinding.ContentMainBinding
import com.fededri.kmmdemo.android.views.MovieView
import com.fededri.kmmdemo.models.Movie
import com.fededri.kmmdemo.movies.MovieEvents
import com.fededri.kmmdemo.movies.MoviesActions
import com.fededri.kmmdemo.movies.MoviesState
import com.fededri.kmmdemo.movies.MoviesViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        LaunchedEffect(viewModel) {
            viewModel.observeEvents().collect { event ->
                processEvent(event)
            }
        }

        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = "Movies")
            }, navigationIcon = {
                IconButton(onClick = { viewModel.action(MoviesActions.RandomizeMoviesList) }) {
                    Icon(Icons.Filled.Shuffle, "Shuffle")
                }
            })
        }) {
            AndroidViewBinding(ContentMainBinding::inflate)
        }
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
