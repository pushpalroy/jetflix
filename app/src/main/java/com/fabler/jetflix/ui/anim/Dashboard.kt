package com.fabler.jetflix.ui.anim

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import com.fabler.jetflix.ui.theme.JetFlixTheme

@Composable
fun getIconTint(selected: Boolean): State<Color> {
  return animateColorAsState(
    if (selected) {
      JetFlixTheme.colors.iconInteractive
    } else {
      JetFlixTheme.colors.iconInteractiveInactive
    }
  )
}