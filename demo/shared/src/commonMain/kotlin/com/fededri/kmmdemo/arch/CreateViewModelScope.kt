package com.fededri.kmmdemo.arch

import com.fededri.kmmdemo.arch.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
var createViewModelScope: () -> CoroutineScope = {
    CoroutineScope(DispatcherProvider.mainDispatcher() + SupervisorJob())
}