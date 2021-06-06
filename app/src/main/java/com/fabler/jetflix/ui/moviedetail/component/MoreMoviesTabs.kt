package com.fabler.jetflix.ui.moviedetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabler.jetflix.R
import com.fabler.jetflix.ui.moviedetail.component.MoreMoviesCategory.MoreLikeThis
import com.fabler.jetflix.ui.moviedetail.component.MoreMoviesCategory.TrailersAndMore
import com.fabler.jetflix.ui.theme.JetFlixTheme

enum class MoreMoviesCategory {
  MoreLikeThis,
  TrailersAndMore
}

@Composable
fun MoreMoviesTabs(
  modifier: Modifier = Modifier,
  categories: List<MoreMoviesCategory> = listOf(
    MoreLikeThis, TrailersAndMore
  ),
  selectedCategory: MoreMoviesCategory,
  onCategorySelected: (MoreMoviesCategory) -> Unit
) {
  val selectedIndex = categories.indexOfFirst { it == selectedCategory }
  val indicator = @Composable { tabPositions: List<TabPosition> ->
    HomeCategoryTabIndicator(
      Modifier.tabIndicatorOffset(tabPositions[selectedIndex])
    )
  }

  Column {
    Divider(
      color = JetFlixTheme.colors.uiLighterBackground,
      thickness = 1.dp,
      modifier = Modifier.fillMaxWidth()
    )
    ScrollableTabRow(
      selectedTabIndex = selectedIndex,
      indicator = indicator,
      divider = {},
      edgePadding = 8.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      categories.forEachIndexed { index, category ->
        Tab(
          selected = index == selectedIndex,
          onClick = { onCategorySelected(category) },
          modifier = modifier,
          text = {
            Text(
              text = when (category) {
                MoreLikeThis -> stringResource(R.string.more_like_this)
                TrailersAndMore -> stringResource(R.string.trailers_and_more)
              },
              color = if (index == selectedIndex) {
                JetFlixTheme.colors.textPrimary
              } else {
                JetFlixTheme.colors.textSecondaryDark
              },
              style = TextStyle(
                fontWeight = if (index == selectedIndex) {
                  FontWeight.Bold
                } else {
                  FontWeight.Normal
                },
                fontSize = 12.sp
              )
            )
          }
        )
      }
    }
  }
}

@Composable
fun HomeCategoryTabIndicator(
  modifier: Modifier = Modifier,
  color: Color = JetFlixTheme.colors.accent
) {
  Spacer(
    modifier
      .width(5.dp)
      .height(3.dp)
      .background(color, RoundedCornerShape(percent = 50))
  )
}

@Preview
@Composable
fun MoreMoviesCategoryTabsPreview() {
  JetFlixTheme {
    MoreMoviesTabs(
      categories = listOf(
        MoreLikeThis, TrailersAndMore
      ),
      selectedCategory = MoreLikeThis,
      onCategorySelected = {}
    )
  }
}