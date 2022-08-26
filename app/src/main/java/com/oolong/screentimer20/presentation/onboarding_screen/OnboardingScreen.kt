package com.oolong.screentimer20.presentation.onboarding_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.oolong.screentimer20.Screen
import com.oolong.screentimer20.presentation.onboarding_screen.components.NextButton
import com.oolong.screentimer20.presentation.onboarding_screen.components.PagerScreen

@ExperimentalPagerApi
@Composable
fun OnboardingScreen(
    navController: NavController = rememberNavController()
) {
    val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    )
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onboardingPage = pages[position])
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