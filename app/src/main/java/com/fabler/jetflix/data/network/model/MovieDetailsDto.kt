package com.fabler.jetflix.data.network.model

import com.fabler.jetflix.data.network.constant.MoviesApi
import com.fabler.jetflix.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieDetailsDto(
  val id: Long,
  val adult: Boolean,
  @SerializedName("backdrop_path")
  val backDropPath: String,
  @SerializedName("poster_path")
  val posterPath: String,
  @SerializedName("genres")
  val genres: List<Genres>,
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
    genreIds = genres.map { it.id },
    genres = genres.map { it.name },
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

data class Genres(
  val id: Int,
  val name: String
)