package com.oolong.screentimer20.presentation.countdown_screen

data class CountdownScreenState(
    var hours: String = "00",
    var minutes: String = "00",
    var timeDisplayValue: Int = 0,
    var progressBarPercentage: Float = 100f
)
