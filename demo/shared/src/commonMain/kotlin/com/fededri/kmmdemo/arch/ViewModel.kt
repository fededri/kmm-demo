package com.fededri.kmmdemo.arch

import kotlinx.coroutines.CoroutineScope

expect open class ViewModel() {
    protected val viewModelScope: CoroutineScope

    open fun onCleared()
}