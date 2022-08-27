package com.oolong.screentimer20.presentation.onboarding_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.oolong.screentimer20.Screen
import com.oolong.screentimer20.presentation.onboarding_screen.components.FirstOnBoardingScreen
import com.oolong.screentimer20.presentation.onboarding_screen.components.NextButton
import com.oolong.screentimer20.presentation.onboarding_screen.components.SecondOnBoardingScreen

@ExperimentalPagerApi
@Composable
fun OnboardingScreen(
    navController: NavController = rememberNavController()
) {
    val pagerState = rememberPagerState()

    val backgroundColors = listOf(
        Color(0xFF212121),
        Color(0xFFBB86FC)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = backgroundColors
                )
            )
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 2,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            when (position) {
                0 -> {
                    FirstOnBoardingScreen()
                }
                1 -> {
                    SecondOnBoardingScreen()
                }
            }
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState
        )

        NextButton(
            pagerState = pagerState
        ) {
            navController.navigate(Screen.DeviceAdminActivationScreen.route)
        }
    }
}

@ExperimentalPagerApi
@Composable
@Preview
fun PreviewFirstOnboardingScreen() {
    OnboardingScreen()
}