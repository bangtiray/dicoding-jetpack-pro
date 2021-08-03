package com.bangtiray.movietv.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity
import com.bangtiray.movietv.data.source.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val dao: MovieDao) {
    fun getByType(mType: String): DataSource.Factory<Int, MoviesEntity> = dao.getByType(mType)

    fun getFavByType(mType: String): DataSource.Factory<Int, MoviesEntity> = dao.getFavByType(mType)

    fun getDetailData(id:Int): LiveData<MoviesEntity> = dao.getDetailData(id)

    fun insertData(data: List<MoviesEntity>) = dao.insertMovies(data)

    fun setFavMovie(data: MoviesEntity, newStatus: Boolean) {
        data.isFavorite = newStatus
        dao.updateFavMovie(data)
    }
}