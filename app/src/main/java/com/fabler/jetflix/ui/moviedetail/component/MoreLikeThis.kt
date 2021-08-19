package com.fabler.jetflix.ui.moviedetail.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec.RawRes
import com.airbnb.lottie.compose.rememberLottieComposition
import com.fabler.jetflix.R.raw
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.ui.components.SmallMovieItem
import com.fabler.jetflix.ui.viewmodel.ViewModelProvider
import com.fabler.jetflix.util.Resource.Success

@Composable
fun MoreLikeThis(modifier: Modifier = Modifier) {
  when (val similarMovies = ViewModelProvider.selectedMovieViewModel.similarMovies) {
    is Success -> {
      PhotoGrid(movies = similarMovies.data, modifier = modifier)
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGrid(modifier: Modifier = Modifier, movies: List<Movie?>) {
  Column(modifier = modifier) {
    movies.chunked(4).forEach { movieTriplet ->
      Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        movieTriplet[0]?.let {
          SmallMovieItem(
            it,
            onMovieSelected = {},
            modifier = Modifier.padding(6.dp)
          )
        }
        movieTriplet[1]?.let {
          SmallMovieItem(
            it,
            onMovieSelected = {},
            modifier = Modifier.padding(6.dp)
          )
        }
        movieTriplet[2]?.let {
          SmallMovieItem(
            it,
            onMovieSelected = {},
            modifier = Modifier.padding(6.dp)
          )
        }
      }
    }
  }
}

@Composable
fun TrailersAndMore(modifier: Modifier = Modifier) {
  val composition by rememberLottieComposition(RawRes(raw.working))
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()
  ) {
    LottieAnimation(
      composition,
      modifier = Modifier
        .size(250.dp)
        .padding(10.dp)
        .align(Alignment.Center)
        .then(modifier)
    )
  }
}