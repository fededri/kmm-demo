package com.fededri.kmmdemo.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fededri.kmmdemo.ThreadInfo
import com.fededri.kmmdemo.movies.MoviesViewModel

class ViewModelFactory(private val threadInfo: ThreadInfo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(threadInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}