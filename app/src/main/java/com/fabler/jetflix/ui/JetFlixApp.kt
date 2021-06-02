package com.fabler.jetflix.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fabler.jetflix.ui.components.JetFlixScaffold
import com.fabler.jetflix.ui.dashboard.DashboardSections
import com.fabler.jetflix.ui.dashboard.JetFlixBottomBar
import com.fabler.jetflix.ui.theme.JetFlixTheme
import com.fabler.jetflix.ui.viewmodel.ProvideMultiViewModel
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun JetFlixApp() {
  ProvideWindowInsets {
    JetFlixTheme {
      ProvideMultiViewModel {
        val tabs = remember { DashboardSections.values() }
        val navController = rememberNavController()

        JetFlixScaffold(
          bottomBar = { JetFlixBottomBar(navController = navController, tabs = tabs) }
        ) { innerPaddingModifier ->
          JetFlixNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPaddingModifier)
          )
        }
      }
    }
  }
}