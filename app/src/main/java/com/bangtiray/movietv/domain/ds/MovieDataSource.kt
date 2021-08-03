package com.bangtiray.movietv.domain.ds

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bangtiray.movietv.data.source.Resource
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity

interface MovieDataSource {
    fun getPopularMovies(): LiveData<Resource<PagedList<MoviesEntity>>>
    fun getPopularTv(): LiveData<Resource<PagedList<MoviesEntity>>>
    fun getDetailData(id: Int): LiveData<MoviesEntity>
    fun getListFavoriteMovies(): LiveData<PagedList<MoviesEntity>>
    fun getListFavoriteTv(): LiveData<PagedList<MoviesEntity>>
    fun setFavorite(movie: MoviesEntity, state:Boolean)
}