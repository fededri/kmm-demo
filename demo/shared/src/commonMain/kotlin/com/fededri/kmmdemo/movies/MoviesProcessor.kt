package com.fededri.kmmdemo.movies


import com.fededri.kmmdemo.networking.TMDApi
import io.github.fededri.arch.ThreadInfo
import io.github.fededri.arch.interfaces.Processor

class MoviesProcessor(threadInfo: ThreadInfo) : Processor<MoviesEffects, MoviesActions> {

    private val api : TMDApi = TMDApi(threadInfo)

    override suspend fun dispatchSideEffect(effect: MoviesEffects): MoviesActions {
        return when(effect){
            is MoviesEffects.LoadMovies -> getMovies(effect)
        }
    }

    private suspend fun getMovies(effect: MoviesEffects.LoadMovies) : MoviesActions {
        val movies  = api.getMovies(effect.type)
        return MoviesActions.SaveMovies(movies)
    }
}