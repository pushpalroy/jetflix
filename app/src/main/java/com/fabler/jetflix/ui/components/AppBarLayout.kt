package com.fabler.jetflix.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabler.jetflix.R.drawable
import com.fabler.jetflix.ui.anim.getTopBarColorState
import com.fabler.jetflix.ui.anim.getTopBarWidthState
import com.fabler.jetflix.ui.theme.AlphaNearOpaque
import com.fabler.jetflix.ui.theme.JetFlixTheme

private val TopBarHeight = 80.dp
private val MovieDetailTopBarHeight = 70.dp

@ExperimentalAnimationApi
@Composable
fun JetFlixTopAppBar(
  isScrolledDown: Boolean,
  modifier: Modifier = Modifier,
) {
  JetFlixSurface(
    modifier = modifier
      .padding(top = getTopBarWidthState(isScrolledDown = isScrolledDown).value)
      .height(TopBarHeight),
    color = getTopBarColorState(isScrolledDown = isScrolledDown).value
  ) {
    TopAppBar(
      elevation = 0.dp,
      backgroundColor = Color.Transparent,
      contentColor = JetFlixTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque),
      modifier = modifier
        .height(TopBarHeight)
    ) {
      Column {
        AnimatedVisibility(visible = isScrolledDown.not()) {
          AppBar()
        }
        Spacer(modifier = Modifier.height(20.dp))
        MenuBar()
      }
    }
  }
}

@Composable
fun AppBar(
  showBack: Boolean = false,
  upPress: () -> Unit = {}
) {
  Row(
    modifier = Modifier.padding(start = 10.dp)
  ) {
    Box(
      modifier = Modifier
        .weight(6f)
        .align(Alignment.CenterVertically)
    ) {
      if (showBack) {
        Icon(
          imageVector = Outlined.ArrowBack,
          contentDescription = "Back",
          tint = JetFlixTheme.colors.iconInteractive,
          modifier = Modifier.clickable { upPress() }
        )
      } else {
        Image(
          painterResource(id = drawable.jetflix_logo),
          contentDescription = "Jetflix logo",
          modifier = Modifier
            .size(30.dp)
            .clickable { }
        )
      }
    }
    Box(
      modifier = Modifier
        .weight(1f)
        .align(Alignment.CenterVertically)
        .clickable { }
    ) {
      Icon(
        imageVector = Outlined.Search,
        contentDescription = "Search",
        tint = JetFlixTheme.colors.iconInteractive
      )
    }
    Box(
      modifier = Modifier
        .weight(1f)
        .align(Alignment.CenterVertically)
        .clickable { }
    ) {
      Image(
        painterResource(id = drawable.profile),
        contentDescription = "User profile",
        modifier = Modifier
          .size(25.dp)
      )
    }
  }
}

@Composable
private fun MenuBar() {
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

@Composable
private fun TopAppBarMenuItem(
  text: String,
  modifier: Modifier
) {
  Text(
    text = text,
    color = JetFlixTheme.colors.textSecondary,
    maxLines = 1,
    textAlign = Center,
    style = TextStyle(
      fontWeight = FontWeight.Medium,
      shadow = Shadow(color = Color.Black, blurRadius = 10f),
      fontSize = 16.sp
    ),
    modifier = modifier
      .padding(5.dp)
      .clickable { }
  )
}

@ExperimentalAnimationApi
@Composable
fun MovieDetailAppBar(
  modifier: Modifier = Modifier,
  upPress: () -> Unit
) {
  JetFlixSurface(
    modifier = modifier
      .padding(top = 30.dp)
      .height(MovieDetailTopBarHeight),
    color = Color.Black.copy(alpha = 0.5f)
  ) {
    TopAppBar(
      elevation = 0.dp,
      backgroundColor = Color.Transparent,
      contentColor = JetFlixTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque),
      modifier = modifier
        .height(TopBarHeight)
    ) {
      Column {
        AppBar(showBack = true, upPress = upPress)
      }
    }
  }
}

@ExperimentalAnimationApi
@Preview("JetFlix Top AppBar")
@Composable
fun JetFlixTopAppBarPreview() {
  JetFlixTheme {
    JetFlixTopAppBar(
      isScrolledDown = false
    )
  }
}

@ExperimentalAnimationApi
@Preview(" Movie Detail AppBar")
@Composable
fun MovieDetailTopBarPreview() {
  JetFlixTheme {
    MovieDetailAppBar(
      upPress = {}
    )
  }
}