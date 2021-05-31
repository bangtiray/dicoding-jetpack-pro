package com.bangtiray.movietv.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.bangtiray.movietv.data.source.local.LocalDataSource
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity
import com.bangtiray.movietv.data.source.remote.RemoteDataSource
import com.bangtiray.movietv.utils.LiveDataTestUtil
import com.bangtiray.movietv.utils.MovieDummy
import com.bangtiray.movietv.utils.PagedListUtil
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val repository = FakeMovieRepository(remote, local)

    private val listMovie = MovieDummy.movieDummy()
    private val listTvShow = MovieDummy.movieTVDummy()
    private val movie = MovieDummy.movieDummy()[0]
    private val tvShow = MovieDummy.movieTVDummy()[0]

    @Test
    fun getPopularMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getByType("Movies")).thenReturn(dataSourceFactory)
        repository.getPopularMovies()

        val movie = Resource.success(PagedListUtil.mockPagedList(MovieDummy.movieDummy()))
        verify(local).getByType("Movies")
        org.junit.Assert.assertNotNull(movie.data)
        assertEquals(listMovie.size.toLong(), movie.data?.size?.toLong())
    }

    @Test
    fun getPopularTv() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getByType("TV Show")).thenReturn(dataSourceFactory)
        repository.getPopularTv()

        val tv = Resource.success(PagedListUtil.mockPagedList(MovieDummy.movieTVDummy()))
        verify(local).getByType("TV Show")
        org.junit.Assert.assertNotNull(tv.data)
        assertEquals(listTvShow.size.toLong(), tv.data?.size?.toLong())
    }

    @Test
    fun getDetailMovieData() {
        val movieDummy = MutableLiveData<MoviesEntity>()
        movieDummy.value = movie
        `when`(local.getDetailData(movie.mId)).thenReturn(movieDummy)

        val data = LiveDataTestUtil.getValue(repository.getDetailData(movie.mId))
        verify(local).getDetailData(movie.mId)
        org.junit.Assert.assertNotNull(data)
        assertEquals(movie.mId, data.mId)
    }

    @Test
    fun getDetailTVData() {
        val tvDummy = MutableLiveData<MoviesEntity>()
        tvDummy.value = tvShow
        `when`(local.getDetailData(tvShow.mId)).thenReturn(tvDummy)

        val data = LiveDataTestUtil.getValue(repository.getDetailData(tvShow.mId))
        verify(local).getDetailData(tvShow.mId)
        org.junit.Assert.assertNotNull(data)
        assertEquals(tvShow.mId, data.mId)
    }

    @Test
    fun getListFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getFavByType("Movies")).thenReturn(dataSourceFactory)
        repository.getListFavoriteMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(MovieDummy.movieDummy()))
        verify(local).getFavByType("Movies")
        org.junit.Assert.assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getListFavoriteTV() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getFavByType("TV Show")).thenReturn(dataSourceFactory)
        repository.getListFavoriteTv()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(MovieDummy.movieTVDummy()))
        verify(local).getFavByType("TV Show")
        org.junit.Assert.assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }
}