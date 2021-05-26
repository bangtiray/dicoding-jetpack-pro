package com.bangtiray.movietv.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangtiray.movietv.data.MovieEntity
import com.bangtiray.movietv.data.source.remote.RemoteDataSource
import com.bangtiray.movietv.data.source.remote.callback.GetPopularMovieCallback
import com.bangtiray.movietv.data.source.remote.callback.GetPopularMovieCallbackById
import com.bangtiray.movietv.data.source.remote.callback.GetPopularTVCallback
import com.bangtiray.movietv.data.source.remote.callback.GetPopularTVCallbackById
import com.bangtiray.movietv.data.source.remote.response.MovieResultsItem
import com.bangtiray.movietv.data.source.remote.response.TvShowResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {
    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource)
            }
    }

    override fun getPopularMovies(): LiveData<List<MovieEntity>> {
        val listResults = MutableLiveData<List<MovieEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularMovies(object : GetPopularMovieCallback {
                override fun onLoadPopularMovie(movieResultsItem: List<MovieResultsItem>) {
                    val popularList = ArrayList<MovieEntity>()
                    for (result in movieResultsItem) {
                        val popular = MovieEntity(
                            result.id!!,
                            result.releaseDate.orEmpty(),
                            result.title.orEmpty(),
                            result.overview.orEmpty(),
                            result.voteAverage!!.toFloat(),
                            result.popularity!!.toDouble(),
                            result.backdropPath.orEmpty(),
                            result.posterPath.orEmpty()
                        )
                        popularList.add(popular)
                    }
                    listResults.postValue(popularList)
                }
            })
        }
        return listResults
    }

    override fun getPopularMovieById(id: Int): LiveData<MovieEntity> {
        val data = MutableLiveData<MovieEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularMoviesById(id, object : GetPopularMovieCallbackById {
                override fun onLoadPopularMovieId(result: MovieResultsItem) {

                    val popular = MovieEntity(
                        result.id!!,
                        result.releaseDate.orEmpty(),
                        result.title.orEmpty(),
                        result.overview.orEmpty(),
                        result.voteAverage!!.toFloat(),
                        result.popularity!!.toDouble(),
                        result.backdropPath.orEmpty(),
                        result.posterPath.orEmpty()
                    )

                    data.postValue(popular)
                }
            })
        }
        return data
    }


    override fun getPopularTv(): LiveData<List<MovieEntity>> {
        val listResults = MutableLiveData<List<MovieEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularTV(object : GetPopularTVCallback {
                override fun onLoadPopularTV(tvResultsItem: List<TvShowResultsItem>) {
                    val popularList = ArrayList<MovieEntity>()
                    for (result in tvResultsItem) {
                        val popular = MovieEntity(
                            result.id!!,
                            result.releaseDate.orEmpty(),
                            result.title.orEmpty(),
                            result.overview.orEmpty(),
                            result.voteAverage!!.toFloat(),
                            result.popularity!!.toDouble(),
                            result.backdropPath.orEmpty(),
                            result.posterPath.orEmpty()
                        )
                        popularList.add(popular)
                    }
                    listResults.postValue(popularList)
                }
            })
        }
        return listResults
    }

    override fun getPopularTvById(id: Int): LiveData<MovieEntity> {
        val data = MutableLiveData<MovieEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularTVById(id, object : GetPopularTVCallbackById {
                override fun onLoadPopularTvId(result: TvShowResultsItem) {
                    val popular = MovieEntity(
                        result.id!!,
                        result.releaseDate.orEmpty(),
                        result.title.orEmpty(),
                        result.overview.orEmpty(),
                        result.voteAverage!!.toFloat(),
                        result.popularity!!.toDouble(),
                        result.backdropPath.orEmpty(),
                        result.posterPath.orEmpty()
                    )

                    data.postValue(popular)
                }
            })
        }
        return data
    }


}