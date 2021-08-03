package com.bangtiray.movietv.di

import com.bangtiray.movietv.data.source.MovieRepository
import com.bangtiray.movietv.domain.ds.MovieDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [DatabaseModule::class, NetworkModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: MovieRepository): MovieDataSource

}