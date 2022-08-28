package com.oolong.screentimer20.presentation.duration_entry_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.screentimer20.domain.Keypad

@Composable
fun SingleKey(
    key: Keypad,
    icon: ImageVector? = null,
    onClick: (Keypad) -> Unit = {}
) {
    val backgroundColor = if (key != Keypad.KeyPlay) Color(0xFF212121) else Color(0xFFCD3D3D)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(64.dp))
            .size(width = 64.dp, height = 64.dp)
            .background(backgroundColor)
            .clickable {
                onClick(key)
            }
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                tint = Color.White,
                modifier = Modifier.size(32.dp),
                contentDescription = "Icon"
            )
        } else {
            Text(
                text = key.value,
                style = MaterialTheme.typography.h2
            )
        }
    }
}

@Composable
@Preview
fun PreviewSingleKey() {
    SingleKey(Keypad.Key0)
}