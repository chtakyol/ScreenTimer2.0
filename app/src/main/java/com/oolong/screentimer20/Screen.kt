package com.oolong.screentimer20

sealed class Screen(val route: String) {
    object DurationEntryScreen: Screen(route = "duration_entry_screen")
    object CountdownScreen: Screen(route = "countdown_screen")
}
