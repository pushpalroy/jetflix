package com.fabler.jetflix.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.fabler.jetflix.ui.components.JetFlixScaffold
import com.fabler.jetflix.ui.dashboard.DashboardSections
import com.fabler.jetflix.ui.dashboard.JetFlixBottomBar
import com.fabler.jetflix.ui.dashboard.home.BottomSheetContent
import com.fabler.jetflix.ui.theme.JetFlixTheme
import com.fabler.jetflix.ui.viewmodel.ProvideMultiViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun JetFlixApp() {
  ProvideWindowInsets {
    JetFlixTheme {
      ProvideMultiViewModel {
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
            bottomBar = { JetFlixBottomBar(navController = navController, tabs = tabs) }
          ) { innerPaddingModifier ->
            JetFlixNavGraph(
              navController = navController,
              modifier = Modifier.padding(innerPaddingModifier),
              bottomSheetScaffoldState = bottomSheetScaffoldState,
              coroutineScope = bottomSheetCoroutineScope
            )
          }
        }
      }
    }
  }
}