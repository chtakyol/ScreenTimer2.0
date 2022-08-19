package com.oolong.screentimer20.presentation.countdown_screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CountdownScreen(
    navController: NavController = rememberNavController()
) {
    Text(text = "This is Countdown Screen!")
}

@Composable
@Preview
fun PreviewCountdownScreen()
{

}