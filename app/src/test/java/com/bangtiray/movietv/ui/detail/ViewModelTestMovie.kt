package com.bangtiray.movietv.ui.detail

import com.bangtiray.movietv.utils.MovieDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ViewModelTestMovie {

    private lateinit var viewModel: ViewModel

    private val movie = MovieDummy.movieDummy()[9]

    @Before
    fun setUp() {
        viewModel = ViewModel()
        viewModel.setSelectedMovie(movie.mId)
    }

    @Test
    fun getMovies() {
        val movieEntity = viewModel.getMovies()
        assertNotNull(movieEntity)
        assertEquals(movie.mTitle, movieEntity?.mTitle)
        assertEquals(movie.releaseDate, movieEntity?.releaseDate)
        assertEquals(movie.mOverView, movieEntity?.mOverView)
        assertEquals(movie.mPopularity, movieEntity?.mPopularity)
        assertEquals(movie.mVoteAverage, movieEntity?.mVoteAverage)
        assertEquals(movie.mPosterPath, movieEntity?.mPosterPath)
        assertEquals(movie.mBackDropPath, movieEntity?.mBackDropPath)
    }
}