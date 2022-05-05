package ru.zamilov.nytimesmovie.model

data class Movie(
    val display_title: String,
    val mpaa_rating: String,
    val critics_pick: Int,
    val byline: String,
    val headline: String,
    val summary_short: String,
    val publication_date: String,
    val opening_date: Any?,
    val date_updated: String,
    val link: Link,
    val multimedia: Multimedia,
) {
    data class Link(
        val type: String,
        val url: String,
        val suggested_link_text: String,
    )

    data class Multimedia(
        val type: String,
        val src: String,
        val height: Int,
        val width: Int,
    )
}