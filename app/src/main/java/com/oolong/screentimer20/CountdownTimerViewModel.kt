package com.oolong.screentimer20

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class CountdownTimerViewModel : ViewModel() {
    var arcDegree: Float = 0f
    var isTimerRunning: MutableState<Boolean> = mutableStateOf(false)
    var soundOff: MutableState<Boolean> = mutableStateOf(true)
    var screenOff: MutableState<Boolean> = mutableStateOf(false)
    var deviceAdminActive: MutableState<Boolean> = mutableStateOf(false)
}