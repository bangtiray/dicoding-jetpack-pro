package com.bangtiray.movietv.di

import com.bangtiray.movietv.data.source.MovieRepository
import com.bangtiray.movietv.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieRepository.getInstance(remoteDataSource)
    }
}