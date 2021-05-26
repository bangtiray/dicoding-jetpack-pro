package com.bangtiray.movietv.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    var mId: Int,
    var releaseDate: String,
    var mTitle: String,
    var mOverView: String,
    var mVoteAverage: Float,
    var mPopularity: Double,
    var mBackDropPath: String,
    var mPosterPath: String
) : Parcelable