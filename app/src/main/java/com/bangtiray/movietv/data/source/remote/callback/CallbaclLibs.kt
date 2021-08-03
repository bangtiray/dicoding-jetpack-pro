package com.bangtiray.movietv.data.source.remote.callback

import com.bangtiray.movietv.data.source.remote.response.MovieResultsItem
import com.bangtiray.movietv.data.source.remote.response.TvShowResultsItem

interface GetPopularMovieCallback {
    fun onLoadPopularMovie(movieResultsItem: List<MovieResultsItem>)
}

interface GetPopularMovieCallbackById {
    fun onLoadPopularMovieId(movieResultsItem: MovieResultsItem)
}

interface GetPopularTVCallback {
    fun onLoadPopularTV(tvSowResultsItem: List<TvShowResultsItem>)
}

interface GetPopularTVCallbackById {
    fun onLoadPopularTvId(tvSowResultsItem: TvShowResultsItem)
}
