package com.fededri.kmmdemo

import kotlin.native.concurrent.freeze

internal actual fun <T> T.freeze(): T = freeze()