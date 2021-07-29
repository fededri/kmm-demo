package com.fededri.kmmdemo.movies

import com.fededri.kmmdemo.models.Movie

sealed class MovieEvents {
    data class OpenSelectedMovie(val movie: Movie) : MovieEvents()
}