package com.fabler.jetflix.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.fabler.jetflix.ui.MainDestinations.MOVIE_ID_KEY
import com.fabler.jetflix.ui.dashboard.DashboardSections
import com.fabler.jetflix.ui.dashboard.addDashboardGraph
import com.fabler.jetflix.ui.moviedetail.MovieDetail
import kotlinx.coroutines.CoroutineScope

/**
 * Destinations used in the ([JetFlixApp]).
 */
object MainDestinations {
  const val DASHBOARD_ROUTE = "dashboard"
  const val MOVIE_DETAIL_ROUTE = "movieDetail"
  const val MOVIE_ID_KEY = "movieId"
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun JetFlixNavGraph(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  startDestination: String = MainDestinations.DASHBOARD_ROUTE,
  bottomSheetScaffoldState: BottomSheetScaffoldState,
  coroutineScope: CoroutineScope,
  listState: LazyListState,
  updateAppBarVisibility: (Boolean) -> Unit

) {
  NavHost(
    navController = navController,
    startDestination = startDestination
  ) {
    navigation(
      route = MainDestinations.DASHBOARD_ROUTE,
      startDestination = DashboardSections.HOME.route
    ) {
      addDashboardGraph(
        modifier = modifier,
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        coroutineScope = coroutineScope,
        listState = listState
      )
    }
    composable(
      route = "${MainDestinations.MOVIE_DETAIL_ROUTE}/{$MOVIE_ID_KEY}",
      arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.LongType })
    ) { backStackEntry ->
      val arguments = requireNotNull(backStackEntry.arguments)
      val movieId = arguments.getLong(MOVIE_ID_KEY)
      MovieDetail(
        movieId = movieId,
        upPress = {
          updateAppBarVisibility(true)
          navController.popBackStack()
        }
      )
    }
  }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
fun NavBackStackEntry.lifecycleIsResumed() =
  this.lifecycle.currentState == Lifecycle.State.RESUMED
