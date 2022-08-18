package com.oolong.screentimer20.presentation.countdown_screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CountdownScreen(
    navController: NavController = rememberNavController()
) {
    Text(text = "This is Countdown Screen!")
}