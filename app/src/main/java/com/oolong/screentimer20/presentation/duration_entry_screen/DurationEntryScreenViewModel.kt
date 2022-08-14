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
        val valueAsInt = keypad.value.toInt()
        if (state.digitState < 4) {
            state.timeDisplayValue *= 10
            state.timeDisplayValue += valueAsInt
        }
        state.digitState++
        state.hours.value = if (state.timeDisplayValue/100 < 10)
            "0${(state.timeDisplayValue/100)}" else
                "${(state.timeDisplayValue/100)}"
        state.minutes.value = if (state.timeDisplayValue - state.timeDisplayValue/100 * 100 < 10)
            "0${state.timeDisplayValue - state.timeDisplayValue/100 * 100}" else
                "${(state.timeDisplayValue - state.timeDisplayValue/100 * 100)}"
    }
}