package com.oolong.screentimer20.presentation.duration_entry_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.oolong.screentimer20.domain.Keypad
import com.oolong.screentimer20.utils.getHours
import com.oolong.screentimer20.utils.getMinutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DurationEntryScreenViewModel @Inject constructor(

): ViewModel() {

    var state by mutableStateOf(DurationEntryScreenState())

    fun onEvent(event: DurationEntryScreenEvent) {
        when (event) {
            is DurationEntryScreenEvent.OnKeypadPressed -> {
                when (event.value) {
                    Keypad.KeyDelete -> {
                        deleteValue()
                    }
                    Keypad.KeyPlay -> {

                    }
                    else -> {
                        addValue(event.value)
                    }
                }
            }
        }
    }

    private fun addValue(keypad: Keypad) {
        if (state.digitState < 4) {
            val valueAsInt = keypad.value.toInt()
            state.timeDisplayValue *= 10
            state.timeDisplayValue += valueAsInt
            state.hours.value = state.timeDisplayValue.getHours()
            state.minutes.value = state.timeDisplayValue.getMinutes()
            state.digitState++
        }
    }

    private fun deleteValue() {
        if (state.digitState > 0) {
            state.timeDisplayValue = state.timeDisplayValue / 10
            state.hours.value = state.timeDisplayValue.getHours()
            state.minutes.value = state.timeDisplayValue.getMinutes()
            state.digitState--
        }
    }
}