package com.fabler.jetflix.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.fabler.jetflix.R
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
  ConstraintLayout {
    // Create references for the composables to constrain
    val (movieImage, buttonPanel) = createRefs()

    HighlightMovieItem(topMovie, onMovieClick,
      modifier = modifier.constrainAs(movieImage) {
        top.linkTo(parent.top)
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
private fun MyListButton(
  modifier: Modifier
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.selectable(selected = false, onClick = {})
  ) {
    Icon(
      imageVector = Icons.Default.Check,
      contentDescription = null
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = stringResource(R.string.my_list),
      fontSize = 10.sp,
      style = MaterialTheme.typography.button,
      maxLines = 1
    )
  }
}

@Composable
private fun InfoButton(
  modifier: Modifier
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.selectable(selected = false, onClick = {})
  ) {
    Icon(
      imageVector = Icons.Outlined.Info,
      contentDescription = null
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = stringResource(R.string.info),
      fontSize = 10.sp,
      style = MaterialTheme.typography.button,
      maxLines = 1
    )
  }
}

@Composable
private fun PlayButton(
  isPressed: MutableState<Boolean>,
  modifier: Modifier
) {
  Button(
    onClick = { isPressed.value = isPressed.value.not() },
    colors = ButtonDefaults.buttonColors(
      backgroundColor = Color.White
    ),
    shape = RoundedCornerShape(8),
    modifier = modifier
  )
  {
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
        imageVector = Icons.Default.PlayArrow,
        tint = JetFlixTheme.colors.textInteractive,
        contentDescription = null
      )
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = stringResource(R.string.play),
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = (-0.05).sp,
        color = JetFlixTheme.colors.textInteractive,
        style = MaterialTheme.typography.button,
        maxLines = 1
      )
    }
  }
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