package com.bangtiray.movietv.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bangtiray.movietv.data.source.Resource
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity
import com.bangtiray.movietv.domain.ds.MovieDataSource
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieDataSource: MovieDataSource):MovieUseCase{
    override fun getPopularMovies(): LiveData<Resource<PagedList<MoviesEntity>>> =movieDataSource.getPopularMovies()
    override fun getPopularTv(): LiveData<Resource<PagedList<MoviesEntity>>> =movieDataSource.getPopularTv()
    override fun getDetailData(id: Int): LiveData<MoviesEntity> = movieDataSource.getDetailData(id)

    override fun getListFavoriteMovies(): LiveData<PagedList<MoviesEntity>> =movieDataSource.getListFavoriteMovies()
    override fun getListFavoriteTV(): LiveData<PagedList<MoviesEntity>> = movieDataSource.getListFavoriteTv()

    override fun setFavorite(movie: MoviesEntity, state: Boolean) =movieDataSource.setFavorite(movie, state)

}