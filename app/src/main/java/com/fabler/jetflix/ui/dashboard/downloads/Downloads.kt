package com.fabler.jetflix.ui.dashboard.downloads

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec.RawRes
import com.fabler.jetflix.R.raw

@Composable
fun Downloads(modifier: Modifier = Modifier) {
  val animationSpec = remember { RawRes(raw.download) }
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()
  ) {
    LottieAnimation(
      animationSpec,
      modifier = Modifier
        .size(250.dp)
        .padding(10.dp)
        .align(Alignment.Center)
        .then(modifier)
    )
  }
}