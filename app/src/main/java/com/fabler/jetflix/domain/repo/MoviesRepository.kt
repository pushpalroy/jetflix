package com.fabler.jetflix.domain.repo

import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.domain.model.MovieVideo
import com.fabler.jetflix.util.Single
import com.fabler.jetflix.util.Error

interface MoviesRepository {

  suspend fun getTopRatedMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>>

  suspend fun getNowPlayingMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>>

  suspend fun getPopularMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>>

  suspend fun getMovieById(
    movieId: Long
  ): Single<Error, Movie>

  suspend fun getMovieVideosById(
    movieId: Long
  ): Single<Error, MovieVideo>
}