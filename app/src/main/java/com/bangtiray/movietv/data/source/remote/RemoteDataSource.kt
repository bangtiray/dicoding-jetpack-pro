package com.bangtiray.movietv.data.source.remote

import com.bangtiray.movietv.data.source.remote.api.ApiClient
import com.bangtiray.movietv.data.source.remote.callback.GetPopularMovieCallback
import com.bangtiray.movietv.data.source.remote.callback.GetPopularMovieCallbackById
import com.bangtiray.movietv.data.source.remote.callback.GetPopularTVCallback
import com.bangtiray.movietv.data.source.remote.callback.GetPopularTVCallbackById
import com.bangtiray.movietv.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getPopularMovies(
        callback: GetPopularMovieCallback
    ) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getPopularMovies().await().results?.let {
            callback.onLoadPopularMovie(it)
        }
        EspressoIdlingResource.decrement()
    }

    suspend fun getPopularTV(
        callback: GetPopularTVCallback
    ) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getPopularTv().await().results?.let {
            callback.onLoadPopularTV(it)
        }
        EspressoIdlingResource.decrement()
    }

    suspend fun getPopularMoviesById(id:Int, callback:GetPopularMovieCallbackById) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getPopularMoviesById(id).await().let {
            callback.onLoadPopularMovieId(it)
        }
        EspressoIdlingResource.decrement()
    }

    suspend fun getPopularTVById(id:Int, callback:GetPopularTVCallbackById) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getPopularTvById(id).await().let {
            callback.onLoadPopularTvId(it)
        }
        EspressoIdlingResource.decrement()
    }

}