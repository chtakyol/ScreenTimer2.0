package com.oolong.screentimer20.domain

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TimeUnit(
    value: String = "00",
    unit: String = "Hours"
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = TextStyle(
                color = Color.Cyan,
                fontSize = 64.sp
            )
        )
        Text(
            text = unit,
            style = TextStyle(
                color = Color.Cyan,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
@Preview
fun PreviewTimeUnit() {
    TimeUnit()
}