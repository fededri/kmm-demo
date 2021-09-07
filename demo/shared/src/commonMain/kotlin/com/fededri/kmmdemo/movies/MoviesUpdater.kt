package com.fededri.kmmdemo.movies

import io.github.fededri.arch.Next
import io.github.fededri.arch.interfaces.Updater


typealias  NextResult = Next<MoviesState, MoviesEffects, MovieEvents>

class MoviesUpdater : Updater<MoviesActions, MoviesState, MoviesEffects, MovieEvents> {
    override fun onNewAction(
        action: MoviesActions,
        currentState: MoviesState
    ): Next<MoviesState, MoviesEffects, MovieEvents> {
        return when (action) {
            is MoviesActions.FetchMovies -> fetchMovies(action, currentState)
            is MoviesActions.SaveMovies -> saveMovies(action, currentState)
            is MoviesActions.SelectMovie -> selectMovie(action, currentState)
            is MoviesActions.RandomizeMoviesList -> randomize(action, currentState)
            is MoviesActions.DeselectMovie -> Next.State(currentState.copy(selectedMovie = null))
        }
    }

    private fun randomize(
        action: MoviesActions.RandomizeMoviesList,
        state: MoviesState
    ): NextResult {
        val movies = state.movies.toMutableList()
        movies.shuffle()
        return Next.State(state.copy(movies = movies))
    }

    private fun selectMovie(action: MoviesActions.SelectMovie, state: MoviesState): NextResult {
        return Next.StateWithEvents(
            state.copy(selectedMovie = action.movie),
            setOf(MovieEvents.OpenSelectedMovie(action.movie))
        )
    }

    private fun saveMovies(action: MoviesActions.SaveMovies, state: MoviesState): NextResult {
        return Next.State(state.copy(movies = action.movies))
    }

    private fun fetchMovies(action: MoviesActions.FetchMovies, state: MoviesState): NextResult {
        return Next.StateWithSideEffects(state, setOf(MoviesEffects.LoadMovies(action.type)))
    }
}