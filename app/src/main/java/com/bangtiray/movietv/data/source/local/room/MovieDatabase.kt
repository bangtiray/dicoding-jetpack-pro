package com.bangtiray.movietv.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = true)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}