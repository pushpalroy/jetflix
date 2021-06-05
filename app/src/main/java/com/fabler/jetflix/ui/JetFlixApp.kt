package com.fabler.jetflix.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.fabler.jetflix.ui.anim.getFabTextTextPaddingState
import com.fabler.jetflix.ui.components.JetFlixScaffold
import com.fabler.jetflix.ui.components.JetFlixTopAppBar
import com.fabler.jetflix.ui.dashboard.DashboardSections
import com.fabler.jetflix.ui.dashboard.JetFlixBottomBar
import com.fabler.jetflix.ui.dashboard.home.component.BottomSheetContent
import com.fabler.jetflix.ui.navigation.JetFlixNavGraph
import com.fabler.jetflix.ui.navigation.MainActions
import com.fabler.jetflix.ui.navigation.MainDestinations
import com.fabler.jetflix.ui.theme.JetFlixTheme
import com.fabler.jetflix.ui.viewmodel.ProvideMultiViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun JetFlixApp() {
  ProvideWindowInsets {
    JetFlixTheme {
      ProvideMultiViewModel {

        val (shouldShowAppBar, updateAppBarVisibility) = remember { mutableStateOf(true) }
        val navController = rememberNavController()
        val tabs = remember { DashboardSections.values() }
        val bottomSheetCoroutineScope = rememberCoroutineScope()
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
          bottomSheetState = BottomSheetState(Collapsed)
        )
        val listState = rememberLazyListState()
        val isScrolledDown = remember {
          derivedStateOf {
            listState.firstVisibleItemScrollOffset > 0
          }
        }
        val navActions = remember(navController) {
          MainActions(navController, updateAppBarVisibility)
        }

        BottomSheetScaffold(
          scaffoldState = bottomSheetScaffoldState,
          sheetContent = {
            BottomSheetContent(
              onMovieClick = { movieId: Long ->
                closeBottomSheet(bottomSheetCoroutineScope, bottomSheetScaffoldState)
                navActions.openMovieDetails(movieId)
              },
              onBottomSheetClosePressed = {
                closeBottomSheet(bottomSheetCoroutineScope, bottomSheetScaffoldState)
              }
            )
          },
          sheetPeekHeight = 0.dp
        ) {
          JetFlixScaffold(
            bottomBar = { JetFlixBottomBar(navController = navController, tabs = tabs) },
            fab = {
              if (shouldShowAppBar) {
                PlaySomethingFAB(isScrolledUp = isScrolledDown.value.not())
              }
            }
          ) { innerPaddingModifier ->

            JetFlixNavGraph(
              navController = navController,
              modifier = Modifier.padding(innerPaddingModifier),
              bottomSheetScaffoldState = bottomSheetScaffoldState,
              coroutineScope = bottomSheetCoroutineScope,
              listState = listState,
              actions = navActions
            )
            if (shouldShowAppBar) {
              JetFlixTopAppBar(
                isScrolledDown = isScrolledDown.value,
              )
            }
          }
        }
      }
    }
  }
}

@ExperimentalMaterialApi
private fun closeBottomSheet(
  bottomSheetCoroutineScope: CoroutineScope,
  bottomSheetScaffoldState: BottomSheetScaffoldState
) {
  bottomSheetCoroutineScope.launch {
    bottomSheetScaffoldState.bottomSheetState.collapse()
  }
}

@ExperimentalAnimationApi
@Composable
fun PlaySomethingFAB(
  isScrolledUp: Boolean
) {
  FloatingActionButton(
    onClick = {},
    backgroundColor = JetFlixTheme.colors.progressIndicatorBg,
    elevation = FloatingActionButtonDefaults.elevation(8.dp)
  ) {
    val fabTextPadding = getFabTextTextPaddingState(isScrolledDown = isScrolledUp.not()).value
    Box(
      modifier = Modifier.padding(
        start = fabTextPadding,
        end = fabTextPadding,
      ),
      contentAlignment = Alignment.Center
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Filled.Shuffle,
          contentDescription = "Shuffle",
          tint = JetFlixTheme.colors.iconTint
        )
        Spacer(Modifier.width(fabTextPadding))
        AnimatedVisibility(visible = isScrolledUp) {
          Text(
            text = "Play Something",
            color = JetFlixTheme.colors.uiLightBackground,
            fontWeight = FontWeight.ExtraBold
          )
        }
      }
    }
  }
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