package com.oolong.screentimer20.presentation.onboarding_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FirstOnBoardingScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    top = 54.dp,
                    end = 16.dp
                ),
            text = "Set the time",
            style = MaterialTheme.typography.h1
        )
        Text(
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    end = 16.dp
                ),
            text = "At the end of the time you set, your screen will be turn off.",
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
@Preview
fun PreviewFirstOnBoardingScreen() {
    FirstOnBoardingScreen()
}