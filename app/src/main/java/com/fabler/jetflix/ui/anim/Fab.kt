package com.fabler.jetflix.ui.anim

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val ExtendedFabTextPadding = 20.dp

@Composable
fun getFabTextTextPaddingState(isScrolledDown: Boolean): State<Dp> {
  return animateDpAsState(
    targetValue = if (isScrolledDown) 0.dp else ExtendedFabTextPadding,
  )
}