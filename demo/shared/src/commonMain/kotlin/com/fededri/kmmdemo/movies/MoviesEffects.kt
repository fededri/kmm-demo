package com.fededri.kmmdemo.movies

import com.fededri.kmmdemo.arch.coroutines.DispatcherProvider
import com.fededri.kmmdemo.arch.interfaces.SideEffectInterface
import com.fededri.kmmdemo.models.MovieListType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

sealed class MoviesEffects(
    override val dispatcher: CoroutineDispatcher = DispatcherProvider.backgroundDispatcher(),
    override val coroutineScope: CoroutineScope? = null
) : SideEffectInterface {
    data class LoadMovies(val type: MovieListType) : MoviesEffects()
}