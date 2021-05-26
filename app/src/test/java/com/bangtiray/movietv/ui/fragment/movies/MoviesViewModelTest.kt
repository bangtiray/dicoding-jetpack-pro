package com.bangtiray.movietv.ui.fragment.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bangtiray.movietv.data.MovieEntity
import com.bangtiray.movietv.data.source.MovieRepository
import com.bangtiray.movietv.utils.MovieEntityDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private val moviesDummy = MovieEntityDummy.movieDummy()
    private val tvShowDummy = MovieEntityDummy.movieTVDummy()

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mMovieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(mMovieRepository)
    }

    @Test
    fun testGetMovies() {
        val data = MutableLiveData<List<MovieEntity>>()
        data.value = moviesDummy

        `when`(mMovieRepository.getPopularMovies()).thenReturn(data)
        val list = viewModel.getMovies().value
        verify(mMovieRepository).getPopularMovies()
        assertNotNull(list)
        assertEquals(10, list?.size)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(moviesDummy)
    }

    @Test
    fun testGetTvShow() {
        val data = MutableLiveData<List<MovieEntity>>()
        data.value = tvShowDummy

        `when`(mMovieRepository.getPopularTv()).thenReturn(data)
        val list = viewModel.getTvShow().value
        verify(mMovieRepository).getPopularTv()
        assertNotNull(list)
        assertEquals(10, list?.size)
        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(tvShowDummy)
    }
}