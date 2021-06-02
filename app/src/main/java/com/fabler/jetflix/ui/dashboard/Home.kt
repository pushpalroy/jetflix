package com.fabler.jetflix.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.domain.repo.movies
import com.fabler.jetflix.ui.components.HighlightMovieItem
import com.fabler.jetflix.ui.components.JetFlixSurface
import com.fabler.jetflix.ui.components.LargeMovieItem
import com.fabler.jetflix.ui.theme.JetFlixTheme
import com.fabler.jetflix.ui.viewmodel.ViewModelProvider
import com.fabler.jetflix.util.Resource

@Composable
fun Home(
  onMovieClick: (Long) -> Unit,
  modifier: Modifier = Modifier
) {
  JetFlixSurface(
    color = JetFlixTheme.colors.appBackground,
  ) {
    Column(
      modifier = modifier
        .padding(bottom = 100.dp)
        .verticalScroll(rememberScrollState())
    ) {
      when (val topHighlightedMovie = ViewModelProvider.movieByIdViewModel.movie) {
        is Resource.Success -> {
          TopHighlightedMovie(
            onMovieClick = onMovieClick,
            modifier = modifier,
            topMovie = topHighlightedMovie.data
          )
        }
      }
      Spacer(modifier = Modifier.height(10.dp))
      when (val jetFlixOriginals = ViewModelProvider.topRatedMoviesViewModel.topRatedMovies) {
        is Resource.Success -> {
          JetFlixOriginals(
            onMovieClick = onMovieClick,
            modifier = modifier,
            topRatedMovies = jetFlixOriginals.data
          )
        }
      }
    }
  }
}

@Composable
private fun TopHighlightedMovie(
  onMovieClick: (Long) -> Unit,
  modifier: Modifier = Modifier,
  topMovie: Movie
) {
  HighlightMovieItem(topMovie, onMovieClick, modifier)
}

@Composable
private fun JetFlixOriginals(
  onMovieClick: (Long) -> Unit,
  modifier: Modifier = Modifier,
  topRatedMovies: List<Movie>
) {
  Column(modifier = modifier) {
    Text(
      text = "Jetflix Originals",
      style = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.25.sp
      ),
      color = JetFlixTheme.colors.textPrimary,
      modifier = Modifier.padding(start = 8.dp)
    )
    Spacer(modifier = Modifier.height(12.dp))
    FeaturedAppsList(movies = topRatedMovies, onMovieSelected = onMovieClick)
  }
}

@Composable
private fun FeaturedAppsList(
  movies: List<Movie>,
  onMovieSelected: (Long) -> Unit
) {
  LazyRow(modifier = Modifier.padding(start = 8.dp)) {
    items(movies) { movie ->
      LargeMovieItem(movie, onMovieSelected = onMovieSelected)
      Spacer(modifier = Modifier.width(8.dp))
    }
  }
}

@Preview("Top Highlighted Movie Preview")
@Composable
fun TopHighlightedMoviePreview() {
  JetFlixTheme {
    TopHighlightedMovie(onMovieClick = {}, topMovie = movies.first())
  }
}

@Preview("JetFlix Originals Preview")
@Composable
fun JetFlixOriginalsPreview() {
  JetFlixTheme {
    JetFlixOriginals(onMovieClick = {}, topRatedMovies = movies)
  }
}