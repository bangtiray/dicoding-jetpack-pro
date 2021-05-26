package com.bangtiray.movietv.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bangtiray.movietv.data.source.remote.RemoteDataSource
import com.bangtiray.movietv.data.source.remote.callback.GetPopularMovieCallback
import com.bangtiray.movietv.data.source.remote.callback.GetPopularMovieCallbackById
import com.bangtiray.movietv.data.source.remote.callback.GetPopularTVCallback
import com.bangtiray.movietv.data.source.remote.callback.GetPopularTVCallbackById
import com.bangtiray.movietv.utils.LiveDataTestUtil
import com.bangtiray.movietv.utils.MovieDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val listMovie = MovieDummy.movieDummy()
    private val movieId = listMovie[0].id

    private val listTV = MovieDummy.movieTVDummy()
    private val tvId = listMovie[0].id

    private val movieResponse = MovieDummy.movieDummy()[0]
    private val tvShowResponse = MovieDummy.movieTVDummy()[0]

    @Test
    fun testGetPopularMovies() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as GetPopularMovieCallback).onLoadPopularMovie(listMovie)
                null
            }.`when`(remote).getPopularMovies(any())
        }

        val data = LiveDataTestUtil.getValue(movieRepository.getPopularMovies())
        runBlocking {
            verify(remote).getPopularMovies(any())
        }
        assertNotNull(data)
        assertEquals(listMovie.size.toLong(), data.size.toLong())
    }

    @Test
    fun testGetPopularMovieById() {
        runBlocking {
            doAnswer {
                (it.arguments[1] as GetPopularMovieCallbackById).onLoadPopularMovieId(movieResponse)
                null
            }.`when`(remote).getPopularMoviesById(eq(movieId!!), any())
        }
        val data = LiveDataTestUtil.getValue(movieRepository.getPopularMovieById(movieId!!))

        runBlocking {
            verify(remote).getPopularMoviesById(eq(movieId), any())
        }

        assertNotNull(data)
        assertEquals(movieResponse.id, data.mId)
    }

    @Test
    fun testGetPopularTv() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as GetPopularTVCallback).onLoadPopularTV(listTV)
                null
            }.`when`(remote).getPopularTV(any())
        }

        val data = LiveDataTestUtil.getValue(movieRepository.getPopularTv())
        runBlocking {
            verify(remote).getPopularTV(any())
        }
        assertNotNull(data)
        assertEquals(listTV.size.toLong(), data.size.toLong())
    }

    @Test
    fun testGetPopularTvById() {
        runBlocking {
            doAnswer {
                (it.arguments[1] as GetPopularTVCallbackById).onLoadPopularTvId(tvShowResponse)
                null
            }.`when`(remote).getPopularTVById(eq(tvId!!), any())
        }
        val data = LiveDataTestUtil.getValue(movieRepository.getPopularTvById(tvId!!))

        runBlocking {
            verify(remote).getPopularTVById(eq(tvId), any())
        }

        assertNotNull(data)
        assertEquals(tvShowResponse.id, data.mId)
    }
}
