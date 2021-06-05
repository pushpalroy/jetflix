package com.fabler.jetflix.ui.moviedetail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.fabler.jetflix.ui.components.MovieDetailAppBar
import com.fabler.jetflix.ui.components.VideoPlayer
import timber.log.Timber

@ExperimentalAnimationApi
@Composable
fun MovieDetail(
  movieId: Long,
  upPress: () -> Unit
) {
  Column {
    MovieDetailAppBar(upPress = upPress)
    VideoPlayer(url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
    Timber.d("MovieId: $movieId")
  }
}