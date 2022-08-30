package com.oolong.screentimer20.presentation.splash_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.screentimer20.R
import com.oolong.screentimer20.Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

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
                SplashScreenNavigationEvent.NavigateToOnboardingScreen -> {
                    navController.navigate(Screen.OnboardingScreen.route)
                }
            }
        }
    }

    val backgroundColors = listOf(
        Color(0xFF373737),
        Color(0xFF212121)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = backgroundColors,
                    radius = 400f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_logo),
            contentDescription = "Splash logo"
        )
    }
}

@Composable
@Preview
fun PreviewSplashScreen() {
    val backgroundColors = listOf(
        Color(0xFF373737),
        Color(0xFF212121)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = backgroundColors,
                    radius = 400f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_logo),
            contentDescription = "Splash logo"
        )
    }
}
