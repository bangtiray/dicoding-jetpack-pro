package com.bangtiray.movietv.di

import com.bangtiray.movietv.domain.usecase.MovieInteractor
import com.bangtiray.movietv.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
abstract class MovieAppModule {
    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor):MovieUseCase
}