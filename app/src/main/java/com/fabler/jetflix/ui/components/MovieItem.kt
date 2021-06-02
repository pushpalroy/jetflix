package com.fabler.jetflix.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.domain.repo.movies
import com.fabler.jetflix.ui.theme.JetFlixTheme

@Composable
fun LargeMovieItem(
  movie: Movie,
  onMovieSelected: (Long) -> Unit,
  modifier: Modifier = Modifier
) {
  RoundedCornerRemoteImage(
    imageUrl = movie.posterUrl,
    modifier = modifier
      .width(170.dp)
      .height(320.dp)
      .clickable(onClick = {
        onMovieSelected(movie.id)
      }),
    cornerPercent = 3
  )
}

@Composable
fun HighlightMovieItem(
  movie: Movie,
  onMovieSelected: (Long) -> Unit,
  modifier: Modifier = Modifier
) {
  FullScreenRemoteImage(
    imageUrl = movie.posterUrl,
    modifier = modifier
      .fillMaxWidth()
      .height(550.dp)
      .clickable(onClick = {
        onMovieSelected(movie.id)
      })
      .applyGradient()
  )
}

fun Modifier.applyGradient(): Modifier {
  return drawWithCache {
    val gradient = Brush.verticalGradient(
      colors = listOf(Color.Transparent, Color.Black),
      startY = size.height / 3,
      endY = size.height
    )
    onDrawWithContent {
      drawContent()
      drawRect(gradient, blendMode = BlendMode.Multiply)
    }
  }
}

@Preview("Large Movie Item")
@Composable
fun PlayAppItemPreview() {
  JetFlixTheme {
    val movie = movies.first()
    LargeMovieItem(
      movie = movie,
      onMovieSelected = {}
    )
  }
}