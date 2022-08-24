package com.oolong.screentimer20.presentation.countdown_screen

sealed class CountdownScreenValidationEvent {
    object StartService: CountdownScreenValidationEvent()
    object Idle: CountdownScreenValidationEvent()
}
