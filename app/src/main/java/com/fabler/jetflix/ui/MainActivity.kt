package com.fabler.jetflix.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.view.WindowCompat
import com.fabler.jetflix.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @ExperimentalMaterialApi
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setTheme(R.style.Theme_JetFlix)

    // This app draws behind the system bars, so we want to handle fitting system windows
    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      JetFlixApp()
    }
  }
}