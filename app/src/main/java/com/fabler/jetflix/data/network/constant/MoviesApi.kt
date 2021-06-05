package com.fabler.jetflix.data.network.constant

object MoviesApi {
  const val BASE_URL = "http://api.themoviedb.org/3/"

  const val ENDPOINT_POPULAR = "movie/popular"
  const val ENDPOINT_TOP_RATED = "movie/top_rated"
  const val ENDPOINT_NOW_PLAYING = "movie/now_playing"
  const val ENDPOINT_MOVIE = "movie/{movie_id}"
  const val ENDPOINT_MOVIE_VIDEO = "movie/{movie_id}/videos"

  const val LANG_ENG = "en-US"
  const val IMAGE_BASE_URL_W500 = "https://image.tmdb.org/t/p/w500"
}