package com.oolong.screentimer20.presentation.duration_entry_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.oolong.screentimer20.domain.Keypad
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DurationEntryScreenViewModel @Inject constructor(

): ViewModel() {

    var state by mutableStateOf(DurationEntryScreenState())

    fun onEvent(event: DurationEntryScreenEvent) {
        when (event) {
            is DurationEntryScreenEvent.OnKeypadPressed -> {
                addValue(event.value)
            }
        }
    }

    private fun addValue(keypad: Keypad) {
        // 00:00
        val valueAsInt = keypad.value.toInt()

        var minutesAsInt = 0
        var hoursAsInt = 0

        when (state.digitState) {
            0 -> {
                minutesAsInt = valueAsInt * 1
            }

            1 -> {
                minutesAsInt = valueAsInt * 10
            }
        }

        Log.d("DurationEntry", keypad.value)
    }
}