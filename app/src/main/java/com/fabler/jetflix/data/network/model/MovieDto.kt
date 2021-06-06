package com.fabler.jetflix.data.network.model

import com.fabler.jetflix.data.network.constant.MoviesApi
import com.fabler.jetflix.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesDto(
  val page: Int,
  val results: List<MovieDto>,
  @SerializedName("total_pages")
  val totalPages: Int,
  @SerializedName("total_results")
  val totalResults: Int
)

data class MovieDto(
  val id: Long,
  val adult: Boolean,
  @SerializedName("backdrop_path")
  val backDropPath: String,
  @SerializedName("poster_path")
  val posterPath: String,
  @SerializedName("genre_ids")
  val genreIds: List<Int>,
  @SerializedName("original_language")
  val originalLanguage: String,
  val title: String,
  val overview: String,
  val popularity: Double,
  @SerializedName("release_date")
  val releaseDate: String,
  val video: Boolean,
  @SerializedName("vote_average")
  val avgVote: Double,
  @SerializedName("vote_count")
  val voteCount: Int
) {

  fun asDomainModel() = Movie(
    id = id,
    isAdult = adult,
    backDropUrl = MoviesApi.IMAGE_BASE_URL_W500 + backDropPath,
    posterUrl = MoviesApi.IMAGE_BASE_URL_W500 + posterPath,
    genreIds = genreIds,
    language = originalLanguage,
    title = title,
    overview = overview,
    popularity = popularity,
    releaseDate = releaseDate,
    isVideoAvailable = video,
    avgVote = avgVote,
    voteCount = voteCount
  )
}

data class SimilarMoviesDto(
  val results: List<SimilarMovieDto>
)

data class SimilarMovieDto(
  val id: Int,
  val adult: Boolean,
  @SerializedName("backdrop_path")
  val backDropPath: String,
  @SerializedName("poster_path")
  val posterPath: String,
  @SerializedName("original_language")
  val originalLanguage: String,
  val title: String,
  val overview: String,
  val video: Boolean,
) {
  fun asDomainModel() = Movie(
    id = id.toLong(),
    isAdult = adult,
    backDropUrl = MoviesApi.IMAGE_BASE_URL_W500 + backDropPath,
    posterUrl = MoviesApi.IMAGE_BASE_URL_W500 + posterPath,
    genreIds = listOf(),
    language = originalLanguage,
    title = title,
    overview = overview,
    popularity = 0.0,
    releaseDate = "",
    isVideoAvailable = video,
    avgVote = 0.0,
    voteCount = 0
  )
}