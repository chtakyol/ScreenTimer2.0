package com.oolong.screentimer20.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.oolong.screentimer20.domain.TimeUnit

@Composable
fun TimeDisplay(
    hours: String = "00",
    minutes: String = "00"
) {
    Row {
        TimeUnit(
            value = hours,
            unit = "Hours"
        )
        Text(
            text = ":",
            style = TextStyle(
                color = Color.Cyan,
                fontSize = 64.sp
            )
        )
        TimeUnit(
            value = minutes,
            unit = "Minutes"
        )
    }
}

@Composable
@Preview
fun PreviewTimeDisplay(

) {
    TimeDisplay()
}