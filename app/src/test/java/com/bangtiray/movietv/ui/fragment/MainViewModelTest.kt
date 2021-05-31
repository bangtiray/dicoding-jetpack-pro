package com.bangtiray.movietv.ui.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bangtiray.movietv.data.source.Resource
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity
import com.bangtiray.movietv.domain.usecase.MovieUseCase
import com.bangtiray.movietv.utils.MovieDummy
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private val dummyMovie = MovieDummy.movieDummy()[0]
    private val movieId = dummyMovie.mId
    private val dummyTv = MovieDummy.movieTVDummy()[0]
    private val tvShowId = dummyTv.mId

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var favoritesObserver: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var detailObserver: Observer<MoviesEntity>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Mock
    private lateinit var mUseCase: MovieUseCase

    @Before
    fun setUp() {

        viewModel = MainViewModel(mUseCase)
    }

    @Test
    fun getPopularMovies() {
        val dummyMovie = Resource.success(pagedList)
        `when`(dummyMovie.data?.size).thenReturn(5)
        val movie = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        movie.value = dummyMovie

        `when`(mUseCase.getPopularMovies()).thenReturn(movie)
        val movieEntity = viewModel.getPopularMovies().value?.data
        verify(mUseCase).getPopularMovies()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getPopularMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getPopularTv() {
        val dummyTV = Resource.success(pagedList)
        `when`(dummyTV.data?.size).thenReturn(5)
        val tv = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        tv.value = dummyTV

        `when`(mUseCase.getPopularTv()).thenReturn(tv)
        val movieEntity = viewModel.getPopularTv().value?.data
        verify(mUseCase).getPopularTv()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getPopularTv().observeForever(observer)
        verify(observer).onChanged(dummyTV)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyMovie = pagedList
        `when`(dummyMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MoviesEntity>>()
        movie.value = dummyMovie

        `when`(mUseCase.getListFavoriteMovies()).thenReturn(movie)
        val movieEntity = viewModel.getFavoriteMovie().value
        verify(mUseCase).getListFavoriteMovies()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getFavoriteMovie().observeForever(favoritesObserver)
        verify(favoritesObserver).onChanged(dummyMovie)
    }

    @Test
    fun getFavoriteTV() {
        val dummyTv = pagedList
        `when`(dummyTv.size).thenReturn(5)
        val tv = MutableLiveData<PagedList<MoviesEntity>>()
        tv.value = dummyTv

        `when`(mUseCase.getListFavoriteTV()).thenReturn(tv)
        val movieEntity = viewModel.getFavoriteTV().value
        verify(mUseCase).getListFavoriteTV()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getFavoriteTV().observeForever(favoritesObserver)
        verify(favoritesObserver).onChanged(dummyTv)
    }

    @Test
    fun getDetailMovie() {
        val movieDummy = MutableLiveData<MoviesEntity>()
        movieDummy.value = dummyMovie

        `when`(mUseCase.getDetailData(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.getDetailData(movieId).value

        assertNotNull(movieData)
        assertEquals(dummyMovie.mId, movieData?.mId)
        assertEquals(dummyMovie.mId, movieData?.mId)
        assertEquals(dummyMovie.mTitle, movieData?.mTitle)
        assertEquals(dummyMovie.releaseDate, movieData?.releaseDate)
        assertEquals(dummyMovie.mOverView, movieData?.mOverView)
        assertEquals(dummyMovie.mVoteAverage, movieData?.mVoteAverage)
        assertEquals(dummyMovie.mPopularity, movieData?.mPopularity)
        assertEquals(dummyMovie.mBackDropPath, movieData?.mBackDropPath)
        assertEquals(dummyMovie.mPosterPath, movieData?.mPosterPath)
        assertEquals(dummyMovie.mType, movieData?.mType)
        assertEquals(dummyMovie.isFavorite, movieData?.isFavorite)


        viewModel.getDetailData(movieId).observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTV() {
        val tvDummy = MutableLiveData<MoviesEntity>()
        tvDummy.value = dummyTv

        `when`(mUseCase.getDetailData(tvShowId)).thenReturn(tvDummy)

        val tvData = viewModel.getDetailData(tvShowId).value

        assertNotNull(tvData)
        assertEquals(dummyTv.mId, tvData?.mId)
        assertEquals(dummyTv.mId, tvData?.mId)
        assertEquals(dummyTv.mTitle, tvData?.mTitle)
        assertEquals(dummyTv.releaseDate, tvData?.releaseDate)
        assertEquals(dummyTv.mOverView, tvData?.mOverView)
        assertEquals(dummyTv.mVoteAverage, tvData?.mVoteAverage)
        assertEquals(dummyTv.mPopularity, tvData?.mPopularity)
        assertEquals(dummyTv.mBackDropPath, tvData?.mBackDropPath)
        assertEquals(dummyTv.mPosterPath, tvData?.mPosterPath)
        assertEquals(dummyTv.mType, tvData?.mType)
        assertEquals(dummyTv.isFavorite, tvData?.isFavorite)


        viewModel.getDetailData(tvShowId).observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyTv)
    }

}