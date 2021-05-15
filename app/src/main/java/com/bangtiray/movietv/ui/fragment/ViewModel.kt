package com.bangtiray.movietv.ui.fragment

import androidx.lifecycle.ViewModel
import com.bangtiray.movietv.utils.MovieDummy

class ViewModel:ViewModel() {
    fun genListMovie() = MovieDummy.movieDummy()
    fun genListMovieTV() = MovieDummy.movieTVDummy()
}