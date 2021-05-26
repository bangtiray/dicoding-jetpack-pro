package com.bangtiray.movietv.ui.fragment.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangtiray.movietv.data.MovieEntity
import com.bangtiray.movietv.data.source.MovieRepository

class MoviesViewModel(private val mMovieRepository: MovieRepository) : ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = mMovieRepository.getPopularMovies()
    fun getTvShow(): LiveData<List<MovieEntity>> = mMovieRepository.getPopularTv()
}