package com.fabler.jetflix.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabler.jetflix.ui.anim.getTopBarColorState
import com.fabler.jetflix.ui.anim.getTopBarWidthState
import com.fabler.jetflix.ui.theme.AlphaNearOpaque
import com.fabler.jetflix.ui.theme.JetFlixTheme
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun JetFlixTopAppBar(
  isScrolledDown: Boolean,
  modifier: Modifier = Modifier,
) {
  JetFlixSurface(
    modifier = modifier.padding(top = getTopBarWidthState(isScrolledDown = isScrolledDown).value),
    color = getTopBarColorState(isScrolledDown = isScrolledDown).value
  ) {
    TopAppBar(
      elevation = 0.dp,
      backgroundColor = Color.Transparent,
      contentColor = JetFlixTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque),
      modifier = modifier.statusBarsPadding()
    ) {
      Column {
        Row {
          TopAppBarMenuItem(
            text = "TV Shows",
            modifier = Modifier.weight(1f)
          )
          TopAppBarMenuItem(
            text = "Movies",
            modifier = Modifier.weight(1f)
          )
          TopAppBarMenuItem(
            text = "Categories",
            modifier = Modifier.weight(1f)
          )
        }
      }
    }
  }
}

@Composable
private fun TopAppBarMenuItem(
  text: String,
  modifier: Modifier
) {
  Text(
    text = text,
    color = JetFlixTheme.colors.textSecondary,
    maxLines = 1,
    textAlign = TextAlign.Center,
    style = TextStyle(
      fontWeight = FontWeight.Medium,
      shadow = Shadow(color = Color.Black, blurRadius = 10f),
      fontSize = 16.sp
    ),
    modifier = modifier
  )
}

@Preview
@Composable
fun ToolBarPreview() {
  JetFlixTheme {
    JetFlixTopAppBar(
      isScrolledDown = false
    )
  }
}