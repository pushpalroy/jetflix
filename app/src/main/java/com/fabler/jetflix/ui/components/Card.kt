package com.fabler.jetflix.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fabler.jetflix.ui.theme.JetFlixTheme

@Composable
fun Card(
  modifier: Modifier = Modifier,
  shape: Shape = MaterialTheme.shapes.medium,
  color: Color = JetFlixTheme.colors.uiBackground,
  contentColor: Color = JetFlixTheme.colors.textPrimary,
  border: BorderStroke? = null,
  elevation: Dp = 1.dp,
  content: @Composable () -> Unit
) {
  JetFlixSurface(
    modifier = modifier,
    shape = shape,
    color = color,
    contentColor = contentColor,
    elevation = elevation,
    border = border,
    content = content
  )
}
