package com.oolong.screentimer20.domain

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

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
            style = MaterialTheme.typography.h3
        )
        Text(
            text = unit,
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
@Preview
fun PreviewTimeUnit() {
    TimeUnit()
}