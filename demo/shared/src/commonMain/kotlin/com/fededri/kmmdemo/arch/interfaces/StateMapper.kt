package com.fededri.kmmdemo.arch.interfaces

interface StateMapper<State : Any, RenderState : Any> {
    fun mapToRenderState(state: State): RenderState
}