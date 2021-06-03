package com.fabler.jetflix.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel

object ViewModelProvider {
  val topRatedMoviesViewModel: TopRatedMoviesViewModel
    @Composable
    get() = LocalTopRatedMoviesViewModel.current

  val nowPlayingMoviesViewModel: NowPlayingMoviesViewModel
    @Composable
    get() = LocalNowPlayingMoviesViewModel.current

  val movieByIdViewModel: MovieByIdViewModel
    @Composable
    get() = LocalMovieByIdViewModel.current

  val selectedMovieViewModel: SelectedMovieViewModel
    @Composable
    get() = LocalSelectedMovieViewModel.current
}

@Composable
fun ProvideMultiViewModel(content: @Composable () -> Unit) {
  val topRatedMoviesViewModel: TopRatedMoviesViewModel = viewModel()
  val nowPlayingMoviesViewModel: NowPlayingMoviesViewModel = viewModel()
  val movieByIdViewModel: MovieByIdViewModel = viewModel()
  val selectedMovieViewModel: SelectedMovieViewModel = viewModel()

  CompositionLocalProvider(
    LocalTopRatedMoviesViewModel provides topRatedMoviesViewModel,
  ) {
    CompositionLocalProvider(
      LocalNowPlayingMoviesViewModel provides nowPlayingMoviesViewModel,
    ) {
      CompositionLocalProvider(
        LocalMovieByIdViewModel provides movieByIdViewModel
      ) {
        CompositionLocalProvider(
          LocalSelectedMovieViewModel provides selectedMovieViewModel
        ) {
          content()
        }
      }
    }
  }
}

private val LocalTopRatedMoviesViewModel = staticCompositionLocalOf<TopRatedMoviesViewModel> {
  error("No TopRatedMoviesViewModel provided")
}

private val LocalNowPlayingMoviesViewModel = staticCompositionLocalOf<NowPlayingMoviesViewModel> {
  error("No NowPlayingMoviesViewModel provided")
}

private val LocalMovieByIdViewModel = staticCompositionLocalOf<MovieByIdViewModel> {
  error("No MovieByIdViewModel provided")
}

private val LocalSelectedMovieViewModel = staticCompositionLocalOf<SelectedMovieViewModel> {
  error("No SelectedMovieViewModel provided")
}