package com.fededri.kmmdemo.models

import com.fededri.kmmdemo.freeze

data class Movie(
    val name: String
){
    init {
        freeze()
    }
}
