package com.fededri.kmmdemo.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fededri.kmmdemo.movies.MoviesViewModel
import io.github.fededri.arch.ThreadInfo

class ViewModelFactory(private val threadInfo: ThreadInfo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(threadInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}