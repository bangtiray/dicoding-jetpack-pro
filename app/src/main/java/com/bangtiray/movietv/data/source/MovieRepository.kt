package com.bangtiray.movietv.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bangtiray.movietv.data.source.local.LocalDataSource
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity
import com.bangtiray.movietv.data.source.remote.RemoteDataSource
import com.bangtiray.movietv.data.source.remote.api.ApiResponse
import com.bangtiray.movietv.data.source.remote.response.MovieResultsItem
import com.bangtiray.movietv.data.source.remote.response.TvShowResultsItem
import com.bangtiray.movietv.domain.ds.MovieDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MovieDataSource {
    override fun getPopularMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<MovieResultsItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getByType("Movies"), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResultsItem>>> {
                return remoteDataSource.getPopularMovies()
            }

            override fun saveCallResult(data: List<MovieResultsItem>) {
                val dataArr = ArrayList<MoviesEntity>()
                for (list in data) {
                    val movie = MoviesEntity(
                        mId = list.id,
                        mTitle = list.title.orEmpty(),
                        releaseDate = list.releaseDate.orEmpty(),
                        mOverView = list.overview.orEmpty(),
                        mVoteAverage = list.voteAverage!!.toFloat(),
                        mPopularity = list.popularity!!.toDouble(),
                        mBackDropPath = list.backdropPath.orEmpty(),
                        mPosterPath = list.posterPath.orEmpty(),
                        mType = "Movies",
                        isFavorite = false
                    )
                    dataArr.add(movie)
                }
                localDataSource.insertData(dataArr)
            }

        }.asLiveData()
    }


    override fun getPopularTv(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<TvShowResultsItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getByType("TV Show"), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowResultsItem>>> {
                return remoteDataSource.getPopularTv()
            }

            override fun saveCallResult(data: List<TvShowResultsItem>) {
                val dataArr = ArrayList<MoviesEntity>()
                for (list in data) {
                    val movie = MoviesEntity(
                        mId = list.id,
                        mTitle = list.title.orEmpty(),
                        releaseDate = list.releaseDate.orEmpty(),
                        mOverView = list.overview.orEmpty(),
                        mVoteAverage = list.voteAverage!!.toFloat(),
                        mPopularity = list.popularity!!.toDouble(),
                        mBackDropPath = list.backdropPath.orEmpty(),
                        mPosterPath = list.posterPath.orEmpty(),
                        mType = "TV Show",
                        isFavorite = false
                    )
                    dataArr.add(movie)
                }
                localDataSource.insertData(dataArr)
            }


        }.asLiveData()
    }

    override fun getDetailData(id: Int): LiveData<MoviesEntity> {
        return localDataSource.getDetailData(id)
    }

    override fun getListFavoriteMovies(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavByType("Movies"), config).build()
    }

    override fun getListFavoriteTv(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavByType("TV Show"), config).build()
    }


    override fun setFavorite(movie: MoviesEntity, state: Boolean) {
        CoroutineScope(IO).launch {
            localDataSource.setFavMovie(movie, state)
        }
    }
}