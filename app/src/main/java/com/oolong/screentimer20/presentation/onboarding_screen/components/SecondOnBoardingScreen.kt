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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SecondOnBoardingScreen() {
    val spanStyleColor = Color(0xFFCD3D3D)
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
            text = "Permissions",
            style = MaterialTheme.typography.h1
        )
        Text(
            text = buildAnnotatedString {
                append("To provide turning off the screen we need to register the app as device admin. Before remove the app you need to disable admin privilege.\n")
                withStyle(
                    style = SpanStyle(color = spanStyleColor)
                ) {
                    append("Before remove the app you need to disable admin privilege.\n")
                }
                append("You can find this preference with searching")
                withStyle(
                    style = SpanStyle(color = spanStyleColor)
                ) {
                    append(" 'Device Admin Apps'")
                }
                append(" in phone setting.")
            },
            modifier = Modifier
                .padding(start = 24.dp, end = 16.dp)
        )
    }
}

@Composable
@Preview
fun PreviewSecondOnBoardingScreen() {
    SecondOnBoardingScreen()
}