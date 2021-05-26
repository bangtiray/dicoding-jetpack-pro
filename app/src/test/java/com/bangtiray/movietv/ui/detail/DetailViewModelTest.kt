package com.bangtiray.movietv.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bangtiray.movietv.data.MovieEntity
import com.bangtiray.movietv.data.source.MovieRepository
import com.bangtiray.movietv.utils.MovieEntityDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private val moviesDummy = MovieEntityDummy.movieDummy()[0]
    private val movieId = moviesDummy.mId
    private val tvDummy = MovieEntityDummy.movieTVDummy()[0]
    private val tvId = tvDummy.mId

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getMovieDetail() {
        val liveData = MutableLiveData<MovieEntity>()
        liveData.value = moviesDummy

        Mockito.`when`(movieRepository.getPopularMovieById(movieId)).thenReturn(liveData)

        val dataVM = viewModel.getMovieDetail(movieId).value as MovieEntity

        Assert.assertNotNull(dataVM)
        assertEquals(moviesDummy.mId, dataVM.mId)
        assertEquals(moviesDummy.releaseDate, dataVM.releaseDate)
        assertEquals(moviesDummy.mTitle, dataVM.mTitle)
        assertEquals(moviesDummy.mOverView, dataVM.mOverView)
        assertEquals(moviesDummy.mVoteAverage, dataVM.mVoteAverage)
        assertEquals(moviesDummy.mPopularity.toInt(), dataVM.mPopularity.toInt())
        assertEquals(moviesDummy.mBackDropPath, dataVM.mBackDropPath)
        assertEquals(moviesDummy.mPosterPath, dataVM.mPosterPath)

        viewModel.getMovieDetail(movieId).observeForever(observer)
        verify(observer).onChanged(moviesDummy)
    }

    @Test
    fun getTvShowDetail() {
        val liveData = MutableLiveData<MovieEntity>()
        liveData.value = tvDummy

        Mockito.`when`(movieRepository.getPopularTvById(tvId)).thenReturn(liveData)

        val dataVM = viewModel.getTvShowDetail(tvId).value as MovieEntity

        Assert.assertNotNull(dataVM)
        assertEquals(tvDummy.mId, dataVM.mId)
        assertEquals(tvDummy.releaseDate, dataVM.releaseDate)
        assertEquals(tvDummy.mTitle, dataVM.mTitle)
        assertEquals(tvDummy.mOverView, dataVM.mOverView)
        assertEquals(tvDummy.mVoteAverage, dataVM.mVoteAverage)
        assertEquals(tvDummy.mPopularity.toInt(), dataVM.mPopularity.toInt())
        assertEquals(tvDummy.mBackDropPath, dataVM.mBackDropPath)
        assertEquals(tvDummy.mPosterPath, dataVM.mPosterPath)

        viewModel.getTvShowDetail(tvId).observeForever(observer)
        verify(observer).onChanged(tvDummy)
    }
}