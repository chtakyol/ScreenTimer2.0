package com.oolong.screentimer20.presentation.countdown_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.screentimer20.R


@Composable
fun StopButton(
    onClick: () -> Unit = {}
) {
    val backgroundColor = Color(0xFFCD3D3D)
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
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_stop_24),
            tint = Color.White,
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