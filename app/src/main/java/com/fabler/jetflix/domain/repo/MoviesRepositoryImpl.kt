package com.fabler.jetflix.domain.repo

import com.fabler.jetflix.BuildConfig
import com.fabler.jetflix.data.network.service.MoviesService
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.domain.model.MovieVideo
import com.fabler.jetflix.util.Error
import com.fabler.jetflix.util.Error.UnexpectedError
import com.fabler.jetflix.util.Single
import com.fabler.jetflix.util.error
import com.fabler.jetflix.util.success
import timber.log.Timber

class MoviesRepositoryImpl(
  private val service: MoviesService
) : MoviesRepository {

  companion object {
    private const val TAG = "MoviesRepo"
  }

  // Use for JetFlix Originals
  override suspend fun getTopRatedMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>> {
    return try {
      val result = service.getTopRatedMovies(
        language = language,
        page = page,
        apiKey = BuildConfig.API_KEY
      ).results.map { it.asDomainModel() }
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }

  // Use for Trending Now
  override suspend fun getNowPlayingMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>> {
    return try {
      val result = service.getNowPlayingMovies(
        language = language,
        page = page,
        apiKey = BuildConfig.API_KEY
      ).results.map { it.asDomainModel() }
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }

  // Use for Popular On JetFlix
  override suspend fun getPopularMovies(
    language: String,
    page: Int
  ): Single<Error, List<Movie>> {
    return try {
      val result = service.getPopularMovies(
        language = language,
        page = page,
        apiKey = BuildConfig.API_KEY
      ).results.map { it.asDomainModel() }
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }

  override suspend fun getMovieById(
    movieId: Long
  ): Single<Error, Movie> {
    return try {
      val result = service.getMovieById(
        movieId = movieId,
        apiKey = BuildConfig.API_KEY
      ).asDomainModel()
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }

  override suspend fun getMovieVideosById(
    movieId: Long
  ): Single<Error, MovieVideo> {
    return try {
      val result = service.getMovieVideosById(
        movieId = movieId,
        apiKey = BuildConfig.API_KEY
      ).results.first().asDomainModel()
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }

  override suspend fun getSimilarMovies(
    movieId: Long
  ): Single<Error, List<Movie>> {
    return try {
      val result = service.getSimilarMovies(
        movieId = movieId,
        apiKey = BuildConfig.API_KEY
      ).results.map { it.asDomainModel() }
      success(result)
    } catch (e: Exception) {
      Timber.tag(TAG).e("Exception: ${e.message}")
      error(UnexpectedError)
    }
  }
}