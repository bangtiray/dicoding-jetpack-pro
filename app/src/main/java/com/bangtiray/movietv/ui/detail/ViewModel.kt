package com.bangtiray.movietv.ui.detail

import androidx.lifecycle.ViewModel
import com.bangtiray.movietv.data.MovieEntity
import com.bangtiray.movietv.utils.MovieDummy

class ViewModel : ViewModel() {
    private lateinit var mId: String
    fun setSelectedMovie(mId: String) {
        this.mId = mId
    }

    fun getMovies(): MovieEntity? {
        var movie: MovieEntity? = null
        for (entity in MovieDummy.movieDummy()) {
            if (entity.mId == mId) {
                movie = entity
            }
        }
        return movie
    }

    fun getMoviesTV(): MovieEntity? {
        var movie: MovieEntity? = null
        for (entity in MovieDummy.movieTVDummy()) {
            if (entity.mId == mId) {
                movie = entity
            }
        }
        return movie
    }
}