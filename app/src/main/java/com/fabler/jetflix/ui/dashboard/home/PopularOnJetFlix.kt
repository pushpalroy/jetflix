package com.fabler.jetflix.ui.dashboard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.ui.components.SmallMovieItem
import com.fabler.jetflix.ui.theme.JetFlixTheme

@Composable
fun PopularOnJetFlix(
  onMovieClick: (Long) -> Unit,
  modifier: Modifier = Modifier,
  popularOnJetFlixMovies: List<Movie>
) {
  Column(modifier = modifier) {
    Text(
      text = "Popular On JetFlix",
      style = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.25.sp
      ),
      color = JetFlixTheme.colors.textPrimary,
      modifier = Modifier.padding(start = 8.dp)
    )
    Spacer(modifier = Modifier.height(12.dp))
    PopularOnJetFlixMoviesCarousel(movies = popularOnJetFlixMovies, onMovieSelected = onMovieClick)
  }
}

@Composable
private fun PopularOnJetFlixMoviesCarousel(
  movies: List<Movie>,
  onMovieSelected: (Long) -> Unit
) {
  LazyRow(modifier = Modifier.padding(start = 8.dp)) {
    items(movies) { movie ->
      SmallMovieItem(movie, onMovieSelected = onMovieSelected)
      Spacer(modifier = Modifier.width(8.dp))
    }
  }
}