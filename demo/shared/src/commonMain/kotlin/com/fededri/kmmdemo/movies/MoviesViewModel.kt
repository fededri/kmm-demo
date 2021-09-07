package com.fededri.kmmdemo.movies

import com.fededri.kmmdemo.models.MovieListType
import io.github.fededri.arch.ArchViewModel
import io.github.fededri.arch.ThreadInfo


class MoviesViewModel(private val threadInfo: ThreadInfo) :
    ArchViewModel<MoviesActions, MoviesState, MoviesEffects, MovieEvents, Nothing>(
        updater = MoviesUpdater(),
        initialState = MoviesState(),
        threadInfo = threadInfo,
        initialEffects = setOf(MoviesEffects.LoadMovies(MovieListType.POPULAR)),
        processor = MoviesProcessor(threadInfo),
    ) {


}






