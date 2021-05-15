package com.bangtiray.movietv.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class MovieEntity(
    var mId: String,
    var releaseDate: String,
    var mTitle: String,
    var mOverView: String,
    var mVoteAverage: Float,
    var mPopularity: Double,
    var mBackDropPath: String,
    var mPosterPath: String
) : Parcelable