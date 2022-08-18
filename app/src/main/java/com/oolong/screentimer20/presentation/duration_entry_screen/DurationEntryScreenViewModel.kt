package com.oolong.screentimer20.presentation.duration_entry_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.screentimer20.domain.Keypad
import com.oolong.screentimer20.utils.getHours
import com.oolong.screentimer20.utils.getMinutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DurationEntryScreenViewModel @Inject constructor(

): ViewModel() {

    private var _uiState = mutableStateOf(DurationEntryScreenState())
    val uiState: State<DurationEntryScreenState> = _uiState

    val validationState = MutableSharedFlow<DurationScreenValidationEvent>()

    fun onEvent(event: DurationEntryScreenEvent) {
        when (event) {
            is DurationEntryScreenEvent.OnKeypadPressed -> {
                when (event.value) {
                    Keypad.KeyDelete -> {
                        deleteValue()
                    }
                    Keypad.KeyPlay -> {
                        viewModelScope.launch {
                            validationState.emit(
                                DurationScreenValidationEvent.Success
                            )
                        }
                    }
                    else -> {
                        addValue(event.value)
                    }
                }
            }
        }
    }

    private fun addValue(keypad: Keypad) {
        if (_uiState.value.digitState < 4) {
            val valueAsInt = keypad.value.toInt()
            _uiState.value.timeDisplayValue  *= 10
            _uiState.value.timeDisplayValue += valueAsInt
            _uiState.value.digitState++
            _uiState.value = _uiState.value.copy(
                hours = _uiState.value.timeDisplayValue.getHours(),
                minutes = _uiState.value.timeDisplayValue.getMinutes(),
                timeDisplayValue = _uiState.value.timeDisplayValue,
                digitState = _uiState.value.digitState
            )
        }
    }

    private fun deleteValue() {
        if (_uiState.value.digitState > 0) {
            _uiState.value.timeDisplayValue = _uiState.value.timeDisplayValue / 10
            _uiState.value.digitState--
            _uiState.value = _uiState.value.copy(
                hours = _uiState.value.timeDisplayValue.getHours(),
                minutes = _uiState.value.timeDisplayValue.getMinutes(),
                timeDisplayValue = _uiState.value.timeDisplayValue,
                digitState = _uiState.value.digitState
            )
        }
    }
}