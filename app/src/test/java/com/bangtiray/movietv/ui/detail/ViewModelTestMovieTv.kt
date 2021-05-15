package com.bangtiray.movietv.ui.detail

import com.bangtiray.movietv.utils.MovieDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ViewModelTestMovieTv{
    private lateinit var viewModel: ViewModel

    private val movieTv = MovieDummy.movieTVDummy()[9]


    @Before
    fun setUp() {
        viewModel = ViewModel()
        viewModel.setSelectedMovie(movieTv.mId)
    }

    @Test
    fun getMoviesTV() {
        val movieTvEntity = viewModel.getMoviesTV()
        assertNotNull(movieTvEntity)
        assertEquals(movieTv.mTitle, movieTvEntity?.mTitle)
        assertEquals(movieTv.releaseDate, movieTvEntity?.releaseDate)
        assertEquals(movieTv.mOverView, movieTvEntity?.mOverView)
        assertEquals(movieTv.mPopularity, movieTvEntity?.mPopularity)
        assertEquals(movieTv.mVoteAverage, movieTvEntity?.mVoteAverage)
        assertEquals(movieTv.mPosterPath, movieTvEntity?.mPosterPath)
        assertEquals(movieTv.mBackDropPath, movieTvEntity?.mBackDropPath)
    }
}