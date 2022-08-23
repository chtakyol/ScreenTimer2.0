package com.oolong.screentimer20.presentation

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.screentimer20.Screen
import com.oolong.screentimer20.presentation.countdown_screen.CountdownScreenValidationEvent
import com.oolong.screentimer20.presentation.splash_screen.SplashScreenNavigationEvent
import com.oolong.screentimer20.presentation.splash_screen.SplashScreenViewModel

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.splashScreenNavigationEvent.collect { event ->
            when(event) {
                SplashScreenNavigationEvent.NavigateToCountdownScreen -> {
                    navController.navigate(Screen.CountdownScreen.route)
                }
                SplashScreenNavigationEvent.NavigateToDurationEntryScreen -> {
                    navController.navigate(Screen.DurationEntryScreen.route)
                }

            }
        }
    }

    Text(text = "This is splash screen!")
}