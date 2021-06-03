package com.fabler.jetflix.ui.dashboard.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.domain.repo.movies
import com.fabler.jetflix.ui.components.JetFlixSurface
import com.fabler.jetflix.ui.components.SmallMovieItem
import com.fabler.jetflix.ui.theme.JetFlixTheme
import com.fabler.jetflix.ui.viewmodel.ViewModelProvider
import com.fabler.jetflix.util.Resource.Success
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun BottomSheetContent(
  onMovieClick: (Long) -> Unit,
  onBottomSheetClosePressed: () -> Unit
) {
  when (val selectedMovie = ViewModelProvider.selectedMovieViewModel.selectedMovie) {
    is Success -> {
      selectedMovie.data?.let { safeSelectedMovie ->
        BottomSheetLayout(
          selectedMovie = safeSelectedMovie,
          onMovieClick = onMovieClick,
          onBottomSheetClosePressed = onBottomSheetClosePressed
        )
      }
    }
  }
}

@Composable
fun BottomSheetLayout(
  selectedMovie: Movie,
  onMovieClick: (Long) -> Unit,
  onBottomSheetClosePressed: () -> Unit
) {
  JetFlixSurface(
    shape = RoundedCornerShape(topStartPercent = 5, topEndPercent = 5),
    color = JetFlixTheme.colors.uiLightBackground,
    modifier = Modifier
      .wrapContentWidth()
      .height(350.dp)
  ) {
    JetFlixSurface(
      shape = RoundedCornerShape(topStartPercent = 5, topEndPercent = 5),
      color = JetFlixTheme.colors.uiLightBackground,
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
      Column(
        modifier = Modifier.padding(12.dp)
      ) {
        Row {
          SmallMovieItem(
            selectedMovie,
            onMovieSelected = onMovieClick
          )
          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Row(
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(
                modifier = Modifier.weight(1f)
              ) {
                Text(
                  text = selectedMovie.title,
                  fontSize = 20.sp,
                  fontWeight = FontWeight.ExtraBold,
                  maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                  Text(
                    text = selectedMovie.releaseDate.substring(0, 4),
                    fontSize = 12.sp,
                    maxLines = 1
                  )
                  Spacer(modifier = Modifier.width(12.dp))
                  Text(
                    text = selectedMovie.avgVote.toString(),
                    fontSize = 12.sp,
                    maxLines = 1
                  )
                  Spacer(modifier = Modifier.width(12.dp))
                  Text(
                    text = selectedMovie.voteCount.toString(),
                    fontSize = 12.sp,
                    maxLines = 1
                  )
                }
              }
              IconButton(
                onClick = { onBottomSheetClosePressed() },
                modifier = Modifier
                  .clip(CircleShape)
                  .background(color = JetFlixTheme.colors.uiLighterBackground)
                  .size(25.dp)
              ) {
                Icon(
                  imageVector = Outlined.Close,
                  tint = JetFlixTheme.colors.iconTint,
                  contentDescription = null,
                )
              }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = selectedMovie.overview,
              fontSize = 14.sp,
              maxLines = 5,
              lineHeight = 18.sp,
              overflow = Ellipsis
            )
          }
        }
      }
    }
  }
}

@ExperimentalMaterialApi
fun onBottomSheetTapped(
  coroutineScope: CoroutineScope,
  bottomSheetScaffoldState: BottomSheetScaffoldState
) {
  coroutineScope.launch {
    try {
      if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
        bottomSheetScaffoldState.bottomSheetState.expand()
      } else {
        bottomSheetScaffoldState.bottomSheetState.collapse()
      }
    } catch (e: IllegalArgumentException) {
      Timber.e("Exception in Bottom Sheet: ${e.message}")
    }
  }
}

@Preview("Bottom Sheet Content")
@Composable
fun BottomSheetContentPreview() {
  JetFlixTheme {
    BottomSheetLayout(
      selectedMovie = movies.last(),
      onMovieClick = {},
      onBottomSheetClosePressed = {}
    )
  }
}