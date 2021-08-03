package com.bangtiray.movietv.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "moviesEntity")
@Parcelize
data class MoviesEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "mId")
    var mId: Int = 0,

    @ColumnInfo(name = "title")
    var mTitle: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "mOverView")
    var mOverView: String,

    @ColumnInfo(name = "mVoteAverage")
    var mVoteAverage: Float,

    @ColumnInfo(name = "mPopularity")
    var mPopularity: Double,

    @ColumnInfo(name = "mBackDropPath")
    var mBackDropPath: String,

    @ColumnInfo(name = "mPosterPath")
    var mPosterPath: String,

    @ColumnInfo(name = "mType")
    var mType: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
):Parcelable
