package com.fededri.kmmdemo.movies

import com.fededri.kmmdemo.ThreadInfo
import com.fededri.kmmdemo.arch.ArchViewModel
import com.fededri.kmmdemo.models.Movie
import com.fededri.kmmdemo.models.MovieListType

class MoviesViewModel(private val threadInfo: ThreadInfo) :
    ArchViewModel<MoviesActions, MoviesState, MoviesEffects, MovieEvents, Nothing>(
        updater = MoviesUpdater(),
        initialState = MoviesState(),
        threadInfo = threadInfo,
        initialEffects = setOf(MoviesEffects.LoadMovies(MovieListType.POPULAR)),
        processor = MoviesProcessor(threadInfo),
    ) {


}






