package com.bangtiray.movietv.data.source.remote.api

import com.bangtiray.movietv.data.source.remote.response.MainResponse
import com.bangtiray.movietv.data.source.remote.response.MovieResultsItem
import com.bangtiray.movietv.data.source.remote.response.TvShowResultsItem
import com.bangtiray.movietv.utils.ConstantValue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String = ConstantValue.apiKey
    ): Call<MainResponse<MovieResultsItem>>

    @GET("movie/{movie_id}")
    fun getPopularMoviesById(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = ConstantValue.apiKey
    ): Call<MovieResultsItem>


    @GET("tv/popular")
    fun getPopularTv(
        @Query("api_key") apiKey: String = ConstantValue.apiKey
    ): Call<MainResponse<TvShowResultsItem>>


    @GET("tv/{tv_id}")
    fun getPopularTvById(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String = ConstantValue.apiKey
    ): Call<TvShowResultsItem>



}