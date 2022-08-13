package com.oolong.screentimer20.presentation.duration_entry_screen

data class DurationEntryScreenState(
    val hours: String = "00",
    val minutes: String = "00",
    var digitState: Int  = 0
)
