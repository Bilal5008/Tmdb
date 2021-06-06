package com.digi.tmdb.feature.movielist.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Movie {
    @SerializedName("poster_path")
    private val posterPath: String? = null

    @SerializedName("adult")
    private val adult = false

    @SerializedName("overview")
    private val overview: String? = null

    @SerializedName("release_date")
    private val releaseDate: String? = null

    @SerializedName("genre_ids")
    private val genreIds: List<Int> = ArrayList()

    @SerializedName("id")
    private val id: Int? = null

    @SerializedName("original_title")
    private val originalTitle: String? = null

    @SerializedName("original_language")
    private val originalLanguage: String? = null

    @SerializedName("title")
    private val title: String? = null

    @SerializedName("backdrop_path")
    private val backdropPath: String? = null

    @SerializedName("popularity")
    private val popularity: Double? = null

    @SerializedName("vote_count")
    private val voteCount: Int? = null

    @SerializedName("video")
    private val video: Boolean? = null

    @SerializedName("vote_average")
    private val voteAverage: Double? = null
}