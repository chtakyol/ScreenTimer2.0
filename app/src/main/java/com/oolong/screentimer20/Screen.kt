package com.oolong.screentimer20

sealed class Screen(val route: String) {
    object OnboardingScreen: Screen(route = "onboarding_screen")
    object DeviceAdminActivationScreen: Screen(route = "device_admin_activation_screen")
    object SplashScreen: Screen(route = "splash_screen")
    object DurationEntryScreen: Screen(route = "duration_entry_screen")
    object CountdownScreen: Screen(route = "countdown_screen")
}
