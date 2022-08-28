package com.oolong.screentimer20.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Title(
    modifier: Modifier = Modifier,
    title: String = "Title"
) {
    Column(
        modifier = modifier
            .padding(
                top = 32.dp,
                start = 16.dp,
                end = 16.dp
            )
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier,
            text = title,
            style = MaterialTheme.typography.h2
        )
    }
}

@Composable
@Preview
fun PreviewTitle() {
    Title()
}