package com.fabler.jetflix.domain.repo

import com.fabler.jetflix.BuildConfig
import com.fabler.jetflix.data.network.service.MoviesService
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.util.Either
import com.fabler.jetflix.util.Failure
import com.fabler.jetflix.util.Failure.UnexpectedFailure
import com.fabler.jetflix.util.error
import com.fabler.jetflix.util.success
import timber.log.Timber

class MoviesRepositoryImpl(
  private val service: MoviesService
) : MoviesRepository {

  companion object {
    private const val TAG = "MoviesRepo"
  }

  override suspend fun getTopRatedMovies(
    language: String,
    page: Int
  ): Either<Failure, List<Movie>> {
    return try {
      val result = service.getTopRatedMovies(
        language = language,
        page = page,
        apiKey = BuildConfig.API_KEY
      ).results.map { it.asDomainModel() }
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedFailure)
    }
  }

  override suspend fun getNowPlayingMovies(
    language: String,
    page: Int
  ): Either<Failure, List<Movie>> {
    return try {
      val result = service.getNowPlayingMovies(
        language = language,
        page = page,
        apiKey = BuildConfig.API_KEY
      ).results.map { it.asDomainModel() }
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedFailure)
    }
  }

  override suspend fun getMovieById(
    movieId: Long
  ): Either<Failure, Movie> {
    return try {
      val result = service.getMovieById(
        movieId = movieId,
        apiKey = BuildConfig.API_KEY
      ).asDomainModel()
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedFailure)
    }
  }
}