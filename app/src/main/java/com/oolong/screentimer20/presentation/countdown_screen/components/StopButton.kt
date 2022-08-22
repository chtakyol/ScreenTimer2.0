package com.oolong.screentimer20.presentation.countdown_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StopButton(
    backgroundColor: Color = Color.Gray,
    textColor: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(64.dp))
            .size(width = 64.dp, height = 64.dp)
            .background(backgroundColor)
            .clickable {
                onClick()
            }
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            tint = textColor,
            modifier = Modifier.size(32.dp),
            contentDescription = "Icon"
        )
    }
}

@Composable
@Preview
fun PreviewStopButton() {
    StopButton()
}