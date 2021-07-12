package com.fededri.kmmdemo.arch

import com.fededri.kmmdemo.ThreadInfo
import com.fededri.kmmdemo.arch.coroutines.DispatcherProvider
import com.fededri.kmmdemo.arch.coroutines.EventsConfiguration
import com.fededri.kmmdemo.arch.interfaces.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.EmptyCoroutineContext

abstract class ArchViewModel<Action : Any, State : Any, SideEffect : SideEffectInterface, Event : Any, RenderState : Any>
constructor(
    private val updater: Updater<Action, State, SideEffect, Event>,
    initialState: State,
    private val threadInfo: ThreadInfo,
    initialEffects: Set<SideEffect>? = null,
    private val processor: Processor<SideEffect, Action>,
    private val stateMapper: StateMapper<State, RenderState>? = null,
    eventsConfiguration: EventsConfiguration = EventsConfiguration(),
    private val coroutineExceptionHandler: CoroutineExceptionHandler? = null,

    ) : ActionsDispatcher<Action> {

    private val state = MutableStateFlow(initialState)
    private val events = MutableSharedFlow<Event>(
        eventsConfiguration.replays,
        eventsConfiguration.extraBufferCapacity,
        eventsConfiguration.backPressureStrategy
    )
    private val renderState by lazy { MutableStateFlow(stateMapper?.mapToRenderState(state.value)) }
    private val viewModelScope: CoroutineScope =
        CoroutineScope(DispatcherProvider.mainDispatcher() + SupervisorJob())

    init {
        if (initialEffects != null) {
            dispatchSideEffects(initialEffects)
        }
    }

    fun observeState(): Flow<State> {
        return state.asStateFlow()
    }

    fun observeRenderState(): Flow<RenderState?> {
        check(stateMapper != null) {
            "In order to observe a RenderState, first you must pass a state mapper into the Arch's constructor"
        }
        return renderState.asStateFlow()
    }

    fun observeEvents(): Flow<Event> {
        return events.asSharedFlow()
    }

    override fun action(action: Action) {
        check(threadInfo.isMainThread()) {
            "Actions must be dispatched from the UI thread"
        }
        val next = updater.onNewAction(action, state.value)
        if (!next.sideEffects.isNullOrEmpty()) {
            dispatchSideEffects(next.sideEffects)
        }

        if (!next.events.isNullOrEmpty()) {
            viewModelScope.launch {
                next.events.forEach {
                    events.emit(it)
                }
            }
        }
        state.value = next.state
        renderState.value = stateMapper?.mapToRenderState(next.state)
    }

    private fun dispatchSideEffects(sideEffects: Set<SideEffect>) {
        sideEffects.forEach { effect ->
            val coroutineScope =
                if (effect.coroutineScope != null) effect.coroutineScope!! else viewModelScope
            val dispatcher =
                if (effect.dispatcher != null) effect.dispatcher!! else coroutineScope.coroutineContext

            val coroutineContext = coroutineExceptionHandler ?: EmptyCoroutineContext
            coroutineScope.launch(coroutineContext) {
                withContext(dispatcher) {
                    val effectAction = processor.dispatchSideEffect(effect)
                    withContext(viewModelScope.coroutineContext) {
                        action(effectAction)
                    }
                }
            }
        }
    }

    /**
     * Should be called when the lifecycle of the ViewModel is finished
     */
    fun destroy() {
        viewModelScope.cancel()
    }
}