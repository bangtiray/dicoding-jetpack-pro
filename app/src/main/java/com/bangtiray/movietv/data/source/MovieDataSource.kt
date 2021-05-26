package com.bangtiray.movietv.data.source

import androidx.lifecycle.LiveData
import com.bangtiray.movietv.data.MovieEntity

interface MovieDataSource {
    fun getPopularMovies(): LiveData<List<MovieEntity>>
    fun getPopularMovieById(id: Int): LiveData<MovieEntity>
    fun getPopularTv(): LiveData<List<MovieEntity>>
    fun getPopularTvById(id: Int): LiveData<MovieEntity>
}