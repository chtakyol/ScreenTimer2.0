package com.oolong.screentimer20.presentation.duration_entry_screen

data class DurationEntryScreenState(
    var hours: String = "00",
    var minutes: String = "00",
    var timeDisplayValue: Int = 0,
    var digitState: Int  = 0
)
