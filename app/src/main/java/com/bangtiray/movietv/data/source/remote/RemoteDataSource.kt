package com.bangtiray.movietv.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangtiray.movietv.data.source.remote.api.ApiResponse
import com.bangtiray.movietv.data.source.remote.api.ApiService
import com.bangtiray.movietv.data.source.remote.callback.GetPopularMovieCallback
import com.bangtiray.movietv.data.source.remote.callback.GetPopularMovieCallbackById
import com.bangtiray.movietv.data.source.remote.callback.GetPopularTVCallback
import com.bangtiray.movietv.data.source.remote.callback.GetPopularTVCallbackById
import com.bangtiray.movietv.data.source.remote.response.MovieResultsItem
import com.bangtiray.movietv.data.source.remote.response.TvShowResultsItem
import com.bangtiray.movietv.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService){
    fun getPopularMovies():LiveData<ApiResponse<List<MovieResultsItem>>>{
        EspressoIdlingResource.increment()
        val results = MutableLiveData<ApiResponse<List<MovieResultsItem>>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getPopularMovie().await()
                results.postValue(ApiResponse.success(response.results!!))
            }catch (e:IOException){
                results.postValue(
                    ApiResponse.error(e.message.toString(), mutableListOf())
                )
            }
        }
        EspressoIdlingResource.decrement()
        return results
    }


    fun getPopularTv():LiveData<ApiResponse<List<TvShowResultsItem>>>{
        EspressoIdlingResource.increment()
        val results = MutableLiveData<ApiResponse<List<TvShowResultsItem>>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getPopularTv().await()
                results.postValue(ApiResponse.success(response.results!!))
            }catch (e:IOException){
                results.postValue(
                    ApiResponse.error(e.message.toString(), mutableListOf())
                )
            }
        }
        EspressoIdlingResource.decrement()
        return results
    }
}
//class RemoteDataSource {
//    companion object {
//        @Volatile
//        private var instance: RemoteDataSource? = null
//
//        fun getInstance(): RemoteDataSource =
//            instance ?: synchronized(this) {
//                instance ?: RemoteDataSource()
//            }
//    }
//
//    suspend fun getPopularMovies(
//        callback: GetPopularMovieCallback
//    ) {
//        EspressoIdlingResource.increment()
//        ApiClient.instance.getPopularMovies().await().results?.let {
//            callback.onLoadPopularMovie(it)
//        }
//        EspressoIdlingResource.decrement()
//    }
//
//    suspend fun getPopularTV(
//        callback: GetPopularTVCallback
//    ) {
//        EspressoIdlingResource.increment()
//        ApiClient.instance.getPopularTv().await().results?.let {
//            callback.onLoadPopularTV(it)
//        }
//        EspressoIdlingResource.decrement()
//    }
//
//    suspend fun getPopularMoviesById(id:Int, callback:GetPopularMovieCallbackById) {
//        EspressoIdlingResource.increment()
//        ApiClient.instance.getPopularMoviesById(id).await().let {
//            callback.onLoadPopularMovieId(it)
//        }
//        EspressoIdlingResource.decrement()
//    }
//
//    suspend fun getPopularTVById(id:Int, callback:GetPopularTVCallbackById) {
//        EspressoIdlingResource.increment()
//        ApiClient.instance.getPopularTvById(id).await().let {
//            callback.onLoadPopularTvId(it)
//        }
//        EspressoIdlingResource.decrement()
//    }
//
//}