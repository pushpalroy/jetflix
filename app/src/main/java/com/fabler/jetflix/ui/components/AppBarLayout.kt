package com.fabler.jetflix.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabler.jetflix.ui.theme.JetFlixTheme
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun AppBarLayout(
  upPress: () -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .statusBarsPadding()
      .padding(horizontal = 16.dp, vertical = 16.dp)
      .size(24.dp)
  ) {
    BackButton(
      modifier = Modifier.weight(1f),
      upPress = upPress
    )
    SearchButton(Modifier)
    MoreButton(Modifier)
  }
}

@Composable
fun BackButton(
  modifier: Modifier,
  upPress: () -> Unit
) {
  JetFlixSurface(modifier = modifier) {
    IconButton(
      onClick = { upPress() }
    ) {
      Icon(
        imageVector = Outlined.ArrowBack,
        tint = JetFlixTheme.colors.iconTint,
        contentDescription = null
      )
    }
  }
}

@Composable
fun MoreButton(modifier: Modifier) {
  IconButton(
    onClick = {},
    modifier = modifier
  ) {
    Icon(
      imageVector = Outlined.MoreVert,
      tint = JetFlixTheme.colors.iconTint,
      contentDescription = null
    )
  }
}

@Composable
fun SearchButton(modifier: Modifier) {
  IconButton(
    onClick = {},
    modifier = modifier
  ) {
    Icon(
      imageVector = Outlined.Search,
      tint = JetFlixTheme.colors.iconTint,
      contentDescription = null
    )
  }
}

@Preview("Back Button")
@Composable
private fun BackButtonPreview() {
  JetFlixTheme {
    JetFlixSurface {
      AppBarLayout(
        upPress = {}
      )
    }
  }
}

@Preview("Back Button Dark")
@Composable
private fun BackButtonDarkPreview() {
  JetFlixTheme(darkTheme = true) {
    JetFlixSurface {
      AppBarLayout(
        upPress = {}
      )
    }
  }
}