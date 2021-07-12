package com.fededri.kmmdemo.movies

import com.fededri.kmmdemo.arch.Next
import com.fededri.kmmdemo.arch.interfaces.Updater

typealias  NextResult = Next<MoviesState, MoviesEffects, Nothing>

class MoviesUpdater : Updater<MoviesActions, MoviesState, MoviesEffects, Nothing> {
    override fun onNewAction(
        action: MoviesActions,
        currentState: MoviesState
    ): Next<MoviesState, MoviesEffects, Nothing> {
        return when (action) {
            is MoviesActions.FetchMovies -> fetchMovies(action, currentState)
            is MoviesActions.SaveMovies -> saveMovies(action, currentState)
        }
    }

    private fun saveMovies(action: MoviesActions.SaveMovies, state: MoviesState): NextResult {
        return Next.State(state.copy(movies = action.movies))
    }

    private fun fetchMovies(action: MoviesActions.FetchMovies, state: MoviesState): NextResult {
        return Next.StateWithSideEffects(state, setOf(MoviesEffects.LoadMovies(action.type)))
    }
}