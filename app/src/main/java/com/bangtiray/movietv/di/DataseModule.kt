package com.bangtiray.movietv.di

import android.content.Context
import androidx.room.Room
import com.bangtiray.movietv.data.source.local.room.MovieDao
import com.bangtiray.movietv.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun providePokemonDao(database: MovieDatabase): MovieDao = database.movieDao()
}