package com.fabler.jetflix.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fabler.jetflix.ui.dashboard.home.viewmodel.MovieByIdViewModel
import com.fabler.jetflix.ui.dashboard.home.viewmodel.NowPlayingMoviesViewModel
import com.fabler.jetflix.ui.dashboard.home.viewmodel.PopularMoviesViewModel
import com.fabler.jetflix.ui.dashboard.home.viewmodel.TopRatedMoviesViewModel
import com.fabler.jetflix.ui.moviedetail.viewmodel.MovieVideosViewModel
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

  val movieVideosViewModel: MovieVideosViewModel
    @Composable
    get() = LocalMovieVideoByIdViewModel.current
}

@Composable
fun ProvideMultiViewModel(content: @Composable () -> Unit) {
  val topRatedMoviesVM: TopRatedMoviesViewModel = viewModel()
  val nowPlayingMoviesVM: NowPlayingMoviesViewModel = viewModel()
  val popularMoviesVM: PopularMoviesViewModel = viewModel()
  val movieByIdVM: MovieByIdViewModel = viewModel()
  val selectedMovieVM: SelectedMovieViewModel = viewModel()
  val videoVM: VideoViewModel = viewModel()
  val movieVideosVM: MovieVideosViewModel = viewModel()

  CompositionLocalProvider(
    LocalTopRatedMoviesViewModel provides topRatedMoviesVM,
  ) {
    CompositionLocalProvider(
      LocalNowPlayingMoviesViewModel provides nowPlayingMoviesVM,
    ) {
      CompositionLocalProvider(
        LocalPopularMoviesViewModel provides popularMoviesVM
      ) {
        CompositionLocalProvider(
          LocalMovieByIdViewModel provides movieByIdVM
        ) {
          CompositionLocalProvider(
            LocalSelectedMovieViewModel provides selectedMovieVM
          ) {
            CompositionLocalProvider(
              LocalVideoViewModel provides videoVM
            ) {
              CompositionLocalProvider(
                LocalMovieVideoByIdViewModel provides movieVideosVM,
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

private val LocalMovieVideoByIdViewModel = staticCompositionLocalOf<MovieVideosViewModel> {
  error("No MovieVideoByIdViewModel provided")
}