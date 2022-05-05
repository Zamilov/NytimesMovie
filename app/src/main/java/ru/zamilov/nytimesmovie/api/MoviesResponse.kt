package ru.zamilov.nytimesmovie.api

import ru.zamilov.nytimesmovie.model.Movie

data class MoviesResponse(
    val status: String,
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Movie>,
)
