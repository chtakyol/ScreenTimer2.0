package com.oolong.screentimer20

sealed class Screen(val route: String) {
    object Splash: Screen(route = "splash_screen")
    object CountdownTimer: Screen(route = "countdown_timer_screen")

    object DurationEntryScreen: Screen(route = "duration_entry_screen")
    object CountdownScreen: Screen(route = "countdown_screen")
}
