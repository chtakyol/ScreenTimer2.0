package com.oolong.screentimer20.presentation.splash_screen

sealed class SplashScreenNavigationEvent {
    object NavigateToOnboardingScreen: SplashScreenNavigationEvent()
    object NavigateToCountdownScreen: SplashScreenNavigationEvent()
    object NavigateToDurationEntryScreen: SplashScreenNavigationEvent()
//    object Idle: SplashScreenNavigationEvent()
}
