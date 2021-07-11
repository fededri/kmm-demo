package com.fededri.kmmdemo.android

import android.os.Looper
import com.fededri.kmmdemo.ThreadInfo

object ThreadInfoImpl : ThreadInfo {
    override fun isMainThread(): Boolean {
        return Looper.getMainLooper() == Looper.myLooper()
    }
}