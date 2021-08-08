package com.fededri.kmmdemo.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.fededri.kmmdemo.movies.MoviesState
import com.fededri.kmmdemo.movies.MoviesViewModel

class MovieDetailFragment : Fragment() {

    private val viewModel: MoviesViewModel by activityViewModels { ViewModelFactory(ThreadInfoImpl) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        setContent {
            val state by viewModel.observeState().collectAsState(initial = MoviesState())
            val movie = state.selectedMovie
            if (movie != null) {
                MovieDetailScreen(movie = movie, onBack = {
                    findNavController().navigateUp()
                })
            } else {
                Text(text = "Please select a movie")
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
    }
}