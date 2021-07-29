package com.fededri.kmmdemo.movies

import com.fededri.kmmdemo.freeze
import com.fededri.kmmdemo.models.Movie

data class MoviesState(
    val movies: List<Movie> = emptyList(),
    val selectedMovie: Movie? = null,
) {
    init {
        freeze()
    }
}