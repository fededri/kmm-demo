package com.fededri.kmmdemo.movies

import com.fededri.kmmdemo.models.Movie
import com.fededri.kmmdemo.models.MovieListType

sealed class MoviesActions {
    data class FetchMovies(val type: MovieListType) : MoviesActions()
    data class SaveMovies(val movies: List<Movie>) : MoviesActions()

    data class SelectMovie(val movie: Movie) : MoviesActions()
    object DeselectMovie : MoviesActions()
    object RandomizeMoviesList : MoviesActions()
}