package com.fabler.jetflix.domain.repo

import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.util.Either
import com.fabler.jetflix.util.Failure

interface MoviesRepository {

  suspend fun getTopRatedMovies(
    language: String,
    page: Int
  ): Either<Failure, List<Movie>>

  suspend fun getMovieById(
    id: Long
  ): Either<Failure, Movie>
}