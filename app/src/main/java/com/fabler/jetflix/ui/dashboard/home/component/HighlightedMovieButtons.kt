package com.fabler.jetflix.ui.dashboard.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabler.jetflix.R.string
import com.fabler.jetflix.ui.theme.JetFlixTheme

@Composable
fun MyListButton(
  modifier: Modifier
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.selectable(selected = false, onClick = {})
  ) {
    Icon(
      imageVector = Icons.Default.Check,
      contentDescription = null
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = stringResource(string.my_list),
      fontSize = 10.sp,
      style = MaterialTheme.typography.button,
      maxLines = 1
    )
  }
}

@Composable
fun InfoButton(
  modifier: Modifier
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.selectable(selected = false, onClick = {})
  ) {
    Icon(
      imageVector = Outlined.Info,
      contentDescription = null
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = stringResource(string.info),
      fontSize = 10.sp,
      style = MaterialTheme.typography.button,
      maxLines = 1
    )
  }
}

@Composable
fun PlayButton(
  isPressed: MutableState<Boolean>,
  modifier: Modifier,
  cornerPercent: Int = 8
) {
  Button(
    onClick = { isPressed.value = isPressed.value.not() },
    colors = ButtonDefaults.buttonColors(
      backgroundColor = Color.White
    ),
    shape = RoundedCornerShape(cornerPercent),
    modifier = modifier
  )
  {
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
        imageVector = Icons.Default.PlayArrow,
        tint = JetFlixTheme.colors.textInteractive,
        contentDescription = null
      )
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = stringResource(string.play),
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = (-0.05).sp,
        color = JetFlixTheme.colors.textInteractive,
        style = MaterialTheme.typography.button,
        maxLines = 1
      )
    }
  }
}

@Composable
fun DownloadButton(
  isPressed: MutableState<Boolean>,
  modifier: Modifier,
  cornerPercent: Int = 8
) {
  Button(
    onClick = { isPressed.value = isPressed.value.not() },
    colors = ButtonDefaults.buttonColors(
      backgroundColor = JetFlixTheme.colors.uiLightBackground
    ),
    shape = RoundedCornerShape(cornerPercent),
    modifier = modifier
  )
  {
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
        imageVector = Icons.Default.Download,
        tint = Color.White,
        contentDescription = null
      )
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = stringResource(string.download),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = (-0.05).sp,
        color = Color.White,
        style = MaterialTheme.typography.button,
        maxLines = 1
      )
    }
  }
}