package com.fededri.kmmdemo.movies


import com.fededri.kmmdemo.arch.coroutines.DispatcherProvider
import com.fededri.kmmdemo.models.MovieListType
import io.github.fededri.arch.interfaces.SideEffectInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

sealed class MoviesEffects(
    override val dispatcher: CoroutineDispatcher = DispatcherProvider.backgroundDispatcher(),
    override val coroutineScope: CoroutineScope? = null
) : SideEffectInterface {
    data class LoadMovies(val type: MovieListType) : MoviesEffects()
}