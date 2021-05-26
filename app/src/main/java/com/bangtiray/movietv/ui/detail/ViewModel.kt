package com.bangtiray.movietv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangtiray.movietv.data.MovieEntity
import com.bangtiray.movietv.data.source.MovieRepository
import com.bangtiray.movietv.utils.MovieDummy

class DetailViewModel (private val mMovieRepository: MovieRepository) : ViewModel() {
    fun getMovieDetail(id: Int): LiveData<MovieEntity> = mMovieRepository.getPopularMovieById(id)

    fun getTvShowDetail(id: Int): LiveData<MovieEntity> = mMovieRepository.getPopularTvById(id)
}