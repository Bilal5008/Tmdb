package com.digi.tmdb.retrofit

enum class Filter(var filter: String) {

    LATEST("latest"),
    NOW_PLAYING("now_playing"),
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    UPCOMING("upcoming"),

}