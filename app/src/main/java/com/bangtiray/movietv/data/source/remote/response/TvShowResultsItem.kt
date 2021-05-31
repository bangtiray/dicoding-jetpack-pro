package com.bangtiray.movietv.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResultsItem (
    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("name")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("first_air_date")
    val releaseDate: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("id")
    val id: Int=0,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
)
