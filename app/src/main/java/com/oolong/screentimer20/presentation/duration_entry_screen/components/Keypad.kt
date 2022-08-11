package com.oolong.screentimer20.presentation.duration_entry_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.screentimer20.domain.Keypad

@Composable
fun Keypad(
    onClick: (Keypad) -> Unit = {}
) {
    Column(
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
                icon = Icons.Default.Delete,
                onClick = onClick
            )
            SingleKey(
                key = Keypad.Key0,
                onClick = onClick
            )
            SingleKey(
                key = Keypad.KeyPlay,
                icon = Icons.Default.PlayArrow,
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