package com.bangtiray.movietv.ui.fragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bangtiray.movietv.data.source.MovieRepository
import com.bangtiray.movietv.data.source.Resource
import com.bangtiray.movietv.data.source.local.entity.MoviesEntity
import com.bangtiray.movietv.domain.usecase.MovieUseCase
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(private val useCase: MovieUseCase) : ViewModel() {
    fun getPopularMovies() = useCase.getPopularMovies()
    fun getPopularTv() = useCase.getPopularTv()
    fun getFavoriteMovie() = useCase.getListFavoriteMovies()
    fun getFavoriteTV() = useCase.getListFavoriteTV()
    fun getDetailData(id: Int) = useCase.getDetailData(id)
    fun setFavoriteMovie(movie: MoviesEntity, newStatus: Boolean) =
        useCase.setFavorite(movie, newStatus)
}

class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    fun getListNowPlayingMovies(): LiveData<Resource<PagedList<MoviesEntity>>> = movieRepository.getPopularMovies()

    fun getListOnTheAirTvShows(): LiveData<Resource<PagedList<MoviesEntity>>> = movieRepository.getPopularTv()

}