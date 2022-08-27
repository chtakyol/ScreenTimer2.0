package com.oolong.screentimer20.presentation.duration_entry_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.screentimer20.domain.Keypad
import com.oolong.screentimer20.R

@Composable
fun Keypad(
    modifier: Modifier = Modifier,
    onClick: (Keypad) -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SingleKey(
                key = Keypad.Key1,
                onClick = onClick
            )
            SingleKey(
                key = Keypad.Key2,
                onClick = onClick
            )
            SingleKey(
                key = Keypad.Key3,
                onClick = onClick
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SingleKey(
                key = Keypad.Key4,
                onClick = onClick
            )
            SingleKey(
                key = Keypad.Key5,
                onClick = onClick
            )
            SingleKey(
                key = Keypad.Key6,
                onClick = onClick
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SingleKey(
                key = Keypad.Key7,
                onClick = onClick
            )
            SingleKey(
                key = Keypad.Key8,
                onClick = onClick
            )
            SingleKey(
                key = Keypad.Key9,
                onClick = onClick
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SingleKey(
                key = Keypad.KeyDelete,
                icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_backspace_24),
                onClick = onClick
            )
            SingleKey(
                key = Keypad.Key0,
                onClick = onClick
            )
            SingleKey(
                key = Keypad.KeyPlay,
                icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_play_arrow_24),
                onClick = onClick
            )
        }
    }
}

@Composable
@Preview
fun PreviewKeypad() {
    Keypad()
}