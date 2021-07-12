package com.fededri.kmmdemo.arch.interfaces

interface ActionsDispatcher<Action : Any> {
    fun action(action: Action)
}