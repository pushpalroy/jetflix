package com.fabler.jetflix.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fabler.jetflix.ui.dashboard.home.viewmodel.MovieByIdViewModel
import com.fabler.jetflix.ui.dashboard.home.viewmodel.NowPlayingMoviesViewModel
import com.fabler.jetflix.ui.dashboard.home.viewmodel.PopularMoviesViewModel
import com.fabler.jetflix.ui.dashboard.home.viewmodel.TopRatedMoviesViewModel
import com.fabler.jetflix.ui.moviedetail.viewmodel.MovieVideoByIdViewModel
import com.fabler.jetflix.ui.moviedetail.viewmodel.VideoViewModel

object ViewModelProvider {
  val topRatedMoviesViewModel: TopRatedMoviesViewModel
    @Composable
    get() = LocalTopRatedMoviesViewModel.current

  val nowPlayingMoviesViewModel: NowPlayingMoviesViewModel
    @Composable
    get() = LocalNowPlayingMoviesViewModel.current

  val popularMoviesViewModel: PopularMoviesViewModel
    @Composable
    get() = LocalPopularMoviesViewModel.current

  val movieByIdViewModel: MovieByIdViewModel
    @Composable
    get() = LocalMovieByIdViewModel.current

  val selectedMovieViewModel: SelectedMovieViewModel
    @Composable
    get() = LocalSelectedMovieViewModel.current

  val videoViewModel: VideoViewModel
    @Composable
    get() = LocalVideoViewModel.current

  val movieVideoByIdViewModel: MovieVideoByIdViewModel
    @Composable
    get() = LocalMovieVideoByIdViewModel.current
}

@Composable
fun ProvideMultiViewModel(content: @Composable () -> Unit) {
  val topRatedMoviesViewModel: TopRatedMoviesViewModel = viewModel()
  val nowPlayingMoviesViewModel: NowPlayingMoviesViewModel = viewModel()
  val popularMoviesViewModel: PopularMoviesViewModel = viewModel()
  val movieByIdViewModel: MovieByIdViewModel = viewModel()
  val selectedMovieViewModel: SelectedMovieViewModel = viewModel()
  val videoViewModel: VideoViewModel = viewModel()
  val movieVideoByIdViewModel: MovieVideoByIdViewModel = viewModel()

  CompositionLocalProvider(
    LocalTopRatedMoviesViewModel provides topRatedMoviesViewModel,
  ) {
    CompositionLocalProvider(
      LocalNowPlayingMoviesViewModel provides nowPlayingMoviesViewModel,
    ) {
      CompositionLocalProvider(
        LocalPopularMoviesViewModel provides popularMoviesViewModel
      ) {
        CompositionLocalProvider(
          LocalMovieByIdViewModel provides movieByIdViewModel
        ) {
          CompositionLocalProvider(
            LocalSelectedMovieViewModel provides selectedMovieViewModel
          ) {
            CompositionLocalProvider(
              LocalVideoViewModel provides videoViewModel
            ) {
              CompositionLocalProvider(
                LocalMovieVideoByIdViewModel provides movieVideoByIdViewModel,
              ) {
                content()
              }
            }
          }
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

private val LocalPopularMoviesViewModel = staticCompositionLocalOf<PopularMoviesViewModel> {
  error("No PopularMoviesViewModel provided")
}

private val LocalMovieByIdViewModel = staticCompositionLocalOf<MovieByIdViewModel> {
  error("No MovieByIdViewModel provided")
}

private val LocalSelectedMovieViewModel = staticCompositionLocalOf<SelectedMovieViewModel> {
  error("No SelectedMovieViewModel provided")
}

private val LocalVideoViewModel = staticCompositionLocalOf<VideoViewModel> {
  error("No VideoViewModel provided")
}

private val LocalMovieVideoByIdViewModel = staticCompositionLocalOf<MovieVideoByIdViewModel> {
  error("No MovieVideoByIdViewModel provided")
}