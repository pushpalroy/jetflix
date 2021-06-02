package com.fabler.jetflix.ui.dashboard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.domain.repo.movies
import com.fabler.jetflix.ui.components.Card
import com.fabler.jetflix.ui.components.HighlightMovieItem
import com.fabler.jetflix.ui.components.JetFlixSurface
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
        .padding(bottom = 120.dp)
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
            jetFlixOriginalMovies = jetFlixOriginals.data
          )
        }
      }
      Spacer(modifier = Modifier.height(20.dp))
      when (val popularOnJetFlix = ViewModelProvider.nowPlayingMoviesViewModel.nowPlayingMovies) {
        is Resource.Success -> {
          PopularOnJetFlix(
            onMovieClick = onMovieClick,
            modifier = modifier,
            popularOnJetFlixMovies = popularOnJetFlix.data
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
  ConstraintLayout {
    // Create references for the composables to constrain
    val (movieImage, buttonPanel, topTrendingBanner) = createRefs()
    HighlightMovieItem(topMovie, onMovieClick,
      modifier = modifier.constrainAs(movieImage) {
        top.linkTo(parent.top)
      }
    )
    TopTrendingBanner(
      modifier = modifier.constrainAs(topTrendingBanner) {
        bottom.linkTo(buttonPanel.top, margin = 24.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
      }
    )
    Row(
      modifier = modifier
        .constrainAs(buttonPanel) {
          bottom.linkTo(parent.bottom, margin = 32.dp)
        }
    ) {
      MyListButton(modifier = modifier.weight(1f))
      PlayButton(isPressed = mutableStateOf(true), modifier = modifier.weight(1f))
      InfoButton(modifier = modifier.weight(1f))
    }
  }
}

@Composable
private fun TopTrendingBanner(
  modifier: Modifier
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Card(
      color = JetFlixTheme.colors.banner,
      shape = RoundedCornerShape(10),
      modifier = modifier
        .size(
          width = 28.dp,
          height = 28.dp
        )
    ) {
      Column(
        modifier = Modifier
          .padding(2.dp)
          .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "TOP",
          fontSize = 8.sp,
          style = MaterialTheme.typography.button,
          maxLines = 1
        )
        Text(
          text = "10",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          style = MaterialTheme.typography.button,
          maxLines = 1
        )
      }
    }
    Spacer(modifier = Modifier.width(4.dp))
    Text(
      text = "#2 in India Today",
      color = JetFlixTheme.colors.textPrimary,
      fontSize = 14.sp,
      letterSpacing = (-0.10).sp,
      fontWeight = FontWeight.Bold,
      style = MaterialTheme.typography.button,
      maxLines = 1
    )
  }
}

@Preview("Top Highlighted Movie Preview")
@Composable
fun TopHighlightedMoviePreview() {
  JetFlixTheme(
    darkTheme = true
  ) {
    TopHighlightedMovie(onMovieClick = {}, topMovie = movies.first())
  }
}

@Preview("JetFlix Originals Preview")
@Composable
fun JetFlixOriginalsPreview() {
  JetFlixTheme(
    darkTheme = true
  ) {
    JetFlixOriginals(onMovieClick = {}, jetFlixOriginalMovies = movies)
  }
}

@Preview("Popular On JetFlix Preview")
@Composable
fun PopularOnJetFlixPreview() {
  JetFlixTheme(
    darkTheme = true
  ) {
    PopularOnJetFlix(onMovieClick = {}, popularOnJetFlixMovies = movies)
  }
}