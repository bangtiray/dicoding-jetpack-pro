package com.bangtiray.movietv.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var mId: Int,
    var releaseDate: String,
    var mTitle: String,
    var mOverView: String,
    var mVoteAverage: Float,
    var mPopularity: Double,
    var mBackDropPath: String,
    var mPosterPath: String,
    var mType: String,
    var isFavorite: Boolean = false
) : Parcelable