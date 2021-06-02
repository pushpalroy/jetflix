package com.fabler.jetflix.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel

object ViewModelProvider {
  val topRatedMoviesViewModel: TopRatedMoviesViewModel
    @Composable
    get() = LocalTopRatedMoviesViewModel.current

  val movieByIdViewModel: MovieByIdViewModel
    @Composable
    get() = LocalMovieByIdViewModel.current
}

@Composable
fun ProvideMultiViewModel(content: @Composable () -> Unit) {
  val topRatedMoviesViewModel: TopRatedMoviesViewModel = viewModel()
  val movieByIdViewModel: MovieByIdViewModel = viewModel()

  CompositionLocalProvider(
    LocalTopRatedMoviesViewModel provides topRatedMoviesViewModel,
  ) {
    CompositionLocalProvider(
      LocalMovieByIdViewModel provides movieByIdViewModel
    ) {
      content()
    }
  }
}

private val LocalTopRatedMoviesViewModel = staticCompositionLocalOf<TopRatedMoviesViewModel> {
  error("No TopRatedMoviesViewModel provided")
}

private val LocalMovieByIdViewModel = staticCompositionLocalOf<MovieByIdViewModel> {
  error("No MovieByIdViewModel provided")
}