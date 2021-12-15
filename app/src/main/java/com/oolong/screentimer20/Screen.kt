package com.oolong.screentimer20

sealed class Screen(val route: String) {
    object Splash: Screen(route = "splash_screen")
    object CountdownTimer: Screen(route = "countdown_timer_screen")
}
