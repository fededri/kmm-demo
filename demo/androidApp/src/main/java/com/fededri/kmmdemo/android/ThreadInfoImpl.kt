package com.fededri.kmmdemo.android

import android.os.Looper
import io.github.fededri.arch.ThreadInfo

object ThreadInfoImpl : ThreadInfo {
    override fun isMainThread(): Boolean {
        return Looper.getMainLooper() == Looper.myLooper()
    }
}