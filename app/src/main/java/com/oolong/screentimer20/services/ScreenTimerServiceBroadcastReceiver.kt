package com.oolong.screentimer20.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.oolong.screentimer20.presentation.countdown_screen.CountdownScreenState

class ScreenTimerServiceBroadcastReceiver: BroadcastReceiver() {
    private val state = mutableStateOf(0)

    fun getDurationData(): MutableState<Int> {
        return state
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val duration = intent?.getIntExtra("Duration", 0) ?: return
        state.value = duration
    }
}