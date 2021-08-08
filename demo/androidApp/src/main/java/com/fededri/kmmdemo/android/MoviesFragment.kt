package com.fededri.kmmdemo.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.fededri.kmmdemo.movies.MoviesActions
import com.fededri.kmmdemo.movies.MoviesState
import com.fededri.kmmdemo.movies.MoviesViewModel

class MoviesFragment : Fragment() {

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

            MoviesListView(state = state, onMovieSelected = { movie ->
                viewModel.action(MoviesActions.SelectMovie(movie))
            })
        }
    }
}