package com.fededri.kmmdemo.models

import com.fededri.kmmdemo.freeze

data class Movie(
    val name: String = "Harry Potter",
    val title: String = "Harry Potter and the prisoner of azkaban",
    val popularity: Double = 5.0,
    val video: Boolean = false,
    val voteAverage: Double = 5.0,
    val voteCount: Int = 1000,
    val overview: String = "Very nice",
    val posterPath: String? = null
){
    init {
        freeze()
    }
}
