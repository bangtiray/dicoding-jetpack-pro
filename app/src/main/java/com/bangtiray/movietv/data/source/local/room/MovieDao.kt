package com.bangtiray.movietv.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM moviesEntity WHERE mType=:mType")
    fun getByType(mType: String): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM moviesEntity WHERE isFavorite = 1 AND  mType=:mType")
    fun getFavByType(mType: String): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM moviesEntity WHERE mId =:id")
    fun getDetailData(id:Int): LiveData<MoviesEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(data: List<MoviesEntity>)

    @Update
    fun updateFavMovie(data: MoviesEntity)
}