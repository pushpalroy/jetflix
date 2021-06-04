package com.fabler.jetflix.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.fabler.jetflix.ui.components.JetFlixScaffold
import com.fabler.jetflix.ui.components.JetFlixTopAppBar
import com.fabler.jetflix.ui.dashboard.DashboardSections
import com.fabler.jetflix.ui.dashboard.JetFlixBottomBar
import com.fabler.jetflix.ui.dashboard.home.BottomSheetContent
import com.fabler.jetflix.ui.theme.JetFlixTheme
import com.fabler.jetflix.ui.viewmodel.ProvideMultiViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun JetFlixApp() {
  ProvideWindowInsets {
    JetFlixTheme {
      ProvideMultiViewModel {
        val listState = rememberLazyListState()
        val isScrolledDown = remember {
          derivedStateOf {
            listState.firstVisibleItemScrollOffset > 0
          }
        }

        val bottomSheetCoroutineScope = rememberCoroutineScope()
        val tabs = remember { DashboardSections.values() }
        val navController = rememberNavController()
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
          bottomSheetState = BottomSheetState(Collapsed)
        )

        BottomSheetScaffold(
          scaffoldState = bottomSheetScaffoldState,
          sheetContent = {
            BottomSheetContent(
              onMovieClick = { movieId: Long ->
                navController.navigate("${MainDestinations.MOVIE_DETAIL_ROUTE}/$movieId")
              },
              onBottomSheetClosePressed = {
                bottomSheetCoroutineScope.launch {
                  bottomSheetScaffoldState.bottomSheetState.collapse()
                }
              }
            )
          },
          sheetPeekHeight = 0.dp
        ) {
          JetFlixScaffold(
            bottomBar = { JetFlixBottomBar(navController = navController, tabs = tabs) },
            floatingActionButton = {
              PlaySomethingFAB(isScrolledUp = isScrolledDown.value.not())
            },
            floatingActionButtonPosition = FabPosition.End
          ) { innerPaddingModifier ->

            JetFlixNavGraph(
              navController = navController,
              modifier = Modifier.padding(innerPaddingModifier),
              bottomSheetScaffoldState = bottomSheetScaffoldState,
              coroutineScope = bottomSheetCoroutineScope,
              listState = listState
            )
            JetFlixTopAppBar(
              isScrolledDown = isScrolledDown.value
            )
          }
        }
      }
    }
  }
}

@ExperimentalAnimationApi
@Composable
fun PlaySomethingFAB(
  isScrolledUp: Boolean
) {
  ExtendedFloatingActionButton(
    icon = {
      Icon(
        imageVector = Filled.Shuffle,
        contentDescription = "Shuffle",
        tint = JetFlixTheme.colors.iconTint
      )
    },
    text = {
      AnimatedVisibility(visible = isScrolledUp) {
        Text(
          text = "Play Something",
          color = JetFlixTheme.colors.uiLightBackground,
          fontWeight = FontWeight.ExtraBold
        )
      }
    },
    backgroundColor = JetFlixTheme.colors.progressIndicatorBg,
    onClick = { },
    elevation = FloatingActionButtonDefaults.elevation(8.dp)
  )
}

@ExperimentalAnimationApi
@Preview("Play Something FAB Preview")
@Composable
fun PlaySomethingFloatingActionButtonPreview() {
  JetFlixTheme(
    darkTheme = true
  ) {
    PlaySomethingFAB(true)
  }
}