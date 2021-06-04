package com.fabler.jetflix.data.network.service

import com.fabler.jetflix.data.network.constant.MoviesApi
import com.fabler.jetflix.data.network.model.MovieDetailsDto
import com.fabler.jetflix.data.network.model.MovieDto
import com.fabler.jetflix.data.network.model.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
  @GET(MoviesApi.ENDPOINT_TOP_RATED)
  suspend fun getTopRatedMovies(
    @Query("language") language: String,
    @Query("page") page: Int,
    @Query("api_key") apiKey: String,
  ): MoviesDto

  @GET(MoviesApi.ENDPOINT_NOW_PLAYING)
  suspend fun getNowPlayingMovies(
    @Query("language") language: String,
    @Query("page") page: Int,
    @Query("api_key") apiKey: String,
  ): MoviesDto

  @GET(MoviesApi.ENDPOINT_POPULAR)
  suspend fun getPopularMovies(
    @Query("language") language: String,
    @Query("page") page: Int,
    @Query("api_key") apiKey: String,
  ): MoviesDto

  @GET(MoviesApi.ENDPOINT_MOVIE)
  suspend fun getMovieById(
    @Path("movieId") movieId: Long,
    @Query("api_key") apiKey: String
  ): MovieDetailsDto
}