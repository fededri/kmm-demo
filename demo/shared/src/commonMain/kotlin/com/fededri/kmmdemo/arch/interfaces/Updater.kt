package com.fededri.kmmdemo.arch.interfaces

import com.fededri.kmmdemo.arch.Next


interface Updater<Action : Any, State : Any, SideEffect : SideEffectInterface, Event : Any> {
    fun onNewAction(action: Action, currentState: State): Next<State, SideEffect, Event>
}