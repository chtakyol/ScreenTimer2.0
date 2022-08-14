package com.oolong.screentimer20.presentation.duration_entry_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class DurationEntryScreenState(
    var hours: MutableState<String> = mutableStateOf("00"),
    var minutes: MutableState<String> = mutableStateOf("00"),
    var hoursAsInt: Int = 0,
    var timeDisplayValue: Int = 0,
    var digitState: Int  = 0
)
