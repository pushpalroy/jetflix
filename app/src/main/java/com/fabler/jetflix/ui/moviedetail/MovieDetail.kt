package com.fabler.jetflix.ui.moviedetail

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabler.jetflix.R.drawable
import com.fabler.jetflix.R.string
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.domain.repo.movies
import com.fabler.jetflix.ui.components.MovieDetailAppBar
import com.fabler.jetflix.ui.dashboard.home.component.DownloadButton
import com.fabler.jetflix.ui.dashboard.home.component.PlayButton
import com.fabler.jetflix.ui.moviedetail.component.MoreLikeThis
import com.fabler.jetflix.ui.moviedetail.component.MoreMoviesCategory.MoreLikeThis
import com.fabler.jetflix.ui.moviedetail.component.MoreMoviesCategory.TrailersAndMore
import com.fabler.jetflix.ui.moviedetail.component.MoreMoviesTabs
import com.fabler.jetflix.ui.moviedetail.component.TrailersAndMore
import com.fabler.jetflix.ui.moviedetail.component.VideoPlayer
import com.fabler.jetflix.ui.theme.JetFlixTheme
import com.fabler.jetflix.ui.viewmodel.ViewModelProvider
import com.fabler.jetflix.util.Resource.Success

@ExperimentalAnimationApi
@Composable
fun MovieDetail(
  movieId: Long,
  upPress: () -> Unit
) {
  Column {
    MovieDetailAppBar(upPress = upPress)

    ViewModelProvider.movieVideosViewModel.fetchMovieVideoById(movieId = movieId)
    val movieVideo: String by ViewModelProvider.movieVideosViewModel.movieVideo
      .observeAsState("")

    if (movieVideo.isNotEmpty()) {
      MovieVideo(videoUrl = movieVideo)
    }

    when (val selectedMovie = ViewModelProvider.selectedMovieViewModel.selectedMovie) {
      is Success -> {
        selectedMovie.data?.let { safeSelectedMovie ->
          Column(
            modifier = Modifier
              .padding(12.dp)
              .verticalScroll(state = rememberScrollState())
          ) {
            MovieDetailLayout(movie = safeSelectedMovie)
            Spacer(modifier = Modifier.height(20.dp))
            MoreMovies(movieId = safeSelectedMovie.id)
          }
        }
      }
    }
  }
}

@Composable
private fun MovieDetailLayout(movie: Movie) {
  Row {
    Image(
      painterResource(id = drawable.jetflix_logo),
      contentDescription = "JetFlix logo",
      modifier = Modifier
        .size(20.dp)
        .clickable { }
    )
    Text(
      text = "FILM",
      fontSize = 13.sp,
      fontWeight = FontWeight.Light,
      modifier = Modifier
        .fillMaxWidth()
        .padding(start = 4.dp)
        .align(Alignment.CenterVertically),
      letterSpacing = 2.sp
    )
  }
  Spacer(modifier = Modifier.height(10.dp))
  Text(
    text = movie.title,
    fontSize = 24.sp,
    fontWeight = FontWeight.ExtraBold,
    modifier = Modifier.fillMaxWidth(),
    maxLines = 1
  )
  Spacer(modifier = Modifier.height(10.dp))
  Row {
    Text(
      text = "98% Match",
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
      color = Color(0xff65b562)
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text(
      text = movie.releaseDate.substring(0, 4),
      fontSize = 12.sp,
      fontWeight = FontWeight.Light,
      color = JetFlixTheme.colors.textSecondaryDark
    )
    Spacer(modifier = Modifier.width(10.dp))
    Box(
      modifier = Modifier
        .background(
          color = JetFlixTheme.colors.uiLighterBackground,
          shape = RoundedCornerShape(8)
        )
        .padding(start = 4.dp, top = 1.dp, end = 4.dp, bottom = 1.dp)
    ) {
      Text(
        text = "7+",
        fontSize = 10.sp,
        fontWeight = FontWeight.Light,
        color = JetFlixTheme.colors.textSecondary
      )
    }
    Spacer(modifier = Modifier.width(10.dp))
    Text(
      text = "1h 25m",
      fontSize = 12.sp,
      fontWeight = FontWeight.Light,
      color = JetFlixTheme.colors.textSecondaryDark
    )
    Spacer(modifier = Modifier.width(10.dp))
    Box(
      modifier = Modifier
        .background(
          color = JetFlixTheme.colors.progressIndicatorBg,
          shape = RoundedCornerShape(8)
        )
        .padding(start = 3.dp, top = 0.dp, end = 3.dp, bottom = 0.dp)
    ) {
      Text(
        text = "HD",
        fontSize = 10.sp,
        fontWeight = FontWeight.Light,
        color = Color.Black,
        letterSpacing = 2.sp
      )
    }
  }
  Spacer(modifier = Modifier.height(15.dp))
  PlayButton(
    isPressed = remember { mutableStateOf(true) },
    modifier = Modifier.fillMaxWidth(),
    cornerPercent = 5
  )
  Spacer(modifier = Modifier.height(8.dp))
  DownloadButton(
    isPressed = remember { mutableStateOf(true) },
    modifier = Modifier.fillMaxWidth(),
    cornerPercent = 5
  )
  Spacer(modifier = Modifier.height(15.dp))
  Text(
    text = movie.overview,
    fontSize = 13.sp,
    textAlign = Justify,
    fontWeight = FontWeight.Light,
    maxLines = 4,
    lineHeight = 18.sp,
    overflow = Ellipsis,
    color = JetFlixTheme.colors.textSecondary
  )
  Spacer(modifier = Modifier.height(8.dp))
  Row {
    Text(
      text = "Average Vote: ",
      fontSize = 12.sp,
      fontWeight = FontWeight.SemiBold,
      modifier = Modifier.align(Alignment.CenterVertically),
      color = JetFlixTheme.colors.textSecondaryDark
    )
    Text(
      text = movie.avgVote.toString(),
      fontSize = 12.sp,
      fontWeight = FontWeight.Light,
      modifier = Modifier.align(Alignment.CenterVertically),
      color = JetFlixTheme.colors.textSecondaryDark
    )
  }
  Spacer(modifier = Modifier.height(20.dp))
  Row {
    ImageButton(
      modifier = Modifier.padding(start = 30.dp, end = 30.dp),
      icon = Icons.Default.Check,
      text = stringResource(string.my_list)
    )
    ImageButton(
      modifier = Modifier.padding(start = 30.dp, end = 30.dp),
      icon = Icons.Outlined.ThumbUp,
      text = stringResource(string.rate)
    )
    ImageButton(
      modifier = Modifier.padding(start = 30.dp, end = 30.dp),
      icon = Icons.Default.Share,
      text = stringResource(string.share)
    )
  }
}

@Composable
fun MoreMovies(movieId: Long) {
  val (currentCategory, setCurrentCategory) = rememberSaveable { mutableStateOf(MoreLikeThis) }
  MoreMoviesTabs(
    selectedCategory = currentCategory,
    onCategorySelected = setCurrentCategory,
    modifier = Modifier.fillMaxWidth()
  )
  ViewModelProvider.selectedMovieViewModel.fetchSimilarMovies(movieId = movieId)
  val tweenSpec = remember { getAnimSpec() }
  Crossfade(
    currentCategory,
    animationSpec = tweenSpec,
    modifier = Modifier.padding(2.dp)
  ) { category ->
    when (category) {
      MoreLikeThis -> MoreLikeThis()
      TrailersAndMore -> TrailersAndMore()
    }
  }
}

private fun getAnimSpec(): TweenSpec<Float> {
  return TweenSpec(
    durationMillis = 600,
    easing = LinearOutSlowInEasing
  )
}

@Composable
fun MovieVideo(videoUrl: String) {
  ViewModelProvider.videoViewModel.extract(LocalContext.current, youtubeLink = videoUrl)
  val extractedVideoUrl: String by ViewModelProvider.videoViewModel.extractedVideoUrl
    .observeAsState("")

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(220.dp)
      .background(Color.Black)
  ) {
    if (extractedVideoUrl.isNotEmpty()) {
      VideoPlayer(url = extractedVideoUrl)
    }
  }
}

@Composable
fun ImageButton(
  modifier: Modifier,
  icon: ImageVector,
  text: String
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.selectable(selected = false, onClick = {})
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = text,
      fontSize = 10.sp,
      style = MaterialTheme.typography.button,
      maxLines = 1
    )
  }
}

@Preview
@Composable
private fun PreviewMovieDetailLayout() {
  JetFlixTheme {
    MovieDetailLayout(movie = movies.first())
  }
}
