package com.fabler.jetflix.ui.moviedetail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.fabler.jetflix.ui.components.MovieDetailAppBar
import com.fabler.jetflix.ui.moviedetail.component.VideoPlayer
import com.fabler.jetflix.ui.viewmodel.ViewModelProvider

@ExperimentalAnimationApi
@Composable
fun MovieDetail(
  movieId: Long,
  upPress: () -> Unit
) {
  Column {
    MovieDetailAppBar(upPress = upPress)

    ViewModelProvider.movieVideoByIdViewModel.fetchMovieVideoById(movieId = movieId)
    val movieVideo: String by ViewModelProvider.movieVideoByIdViewModel.movieVideo
      .observeAsState("")
    if (movieVideo.isNotEmpty()) {
      MovieVideo(videoUrl = movieVideo)
    }
  }
}

@Composable
fun MovieVideo(videoUrl: String) {
  ViewModelProvider.videoViewModel.extract(LocalContext.current, youtubeLink = videoUrl)
  val extractedVideoUrl: String by ViewModelProvider.videoViewModel.extractedVideoUrl
    .observeAsState("")

  if (extractedVideoUrl.isNotEmpty()) {
    VideoPlayer(url = extractedVideoUrl)
  }
}