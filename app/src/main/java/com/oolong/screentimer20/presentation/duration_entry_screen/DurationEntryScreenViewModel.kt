package com.oolong.screentimer20.presentation.duration_entry_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.screentimer20.domain.IAppUtilityDataRepository
import com.oolong.screentimer20.domain.IDurationDataRepository
import com.oolong.screentimer20.domain.Keypad
import com.oolong.screentimer20.domain.model.AppUtilityData
import com.oolong.screentimer20.utils.getHoursForTimeDisplay
import com.oolong.screentimer20.utils.getMinutesForTimeDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.delay

@HiltViewModel
class DurationEntryScreenViewModel @Inject constructor(
    private val durationDataRepository: IDurationDataRepository,
    private val appUtilityDataRepository: IAppUtilityDataRepository
): ViewModel() {

    private var _uiState = mutableStateOf(DurationEntryScreenState())
    val uiState: State<DurationEntryScreenState> = _uiState

    val appUtilityData = AppUtilityData()

    val validationState = MutableSharedFlow<DurationScreenValidationEvent>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(300L)
            loadAppUtilityData()
            loadDurationData()
        }
    }

    fun onEvent(event: DurationEntryScreenEvent) {
        when (event) {
            is DurationEntryScreenEvent.OnKeypadPressed -> {
                when (event.value) {
                    Keypad.KeyDelete -> {
                        deleteValue()
                    }
                    Keypad.KeyPlay -> {
                        viewModelScope.launch {
                            updateDurationData()
                            updateAppUtilityData(true)
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
                hours = _uiState.value.timeDisplayValue.getHoursForTimeDisplay(),
                minutes = _uiState.value.timeDisplayValue.getMinutesForTimeDisplay(),
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
                hours = _uiState.value.timeDisplayValue.getHoursForTimeDisplay(),
                minutes = _uiState.value.timeDisplayValue.getMinutesForTimeDisplay(),
                timeDisplayValue = _uiState.value.timeDisplayValue,
                digitState = _uiState.value.digitState
            )
        }
    }
    private suspend fun updateDurationData() {
        val hours = _uiState.value.timeDisplayValue / 100
        val minutes = _uiState.value.timeDisplayValue % 100
        val durationAsMin = hours * 60 + minutes
        durationDataRepository.updateDurationData(
            _uiState.value.timeDisplayValue,
            _uiState.value.digitState,
            durationAsMin,
            onSuccess = {},
            onError = {}
        )
    }

    private suspend fun loadDurationData() {
        durationDataRepository.getDurationData(
            onSuccess = {
                _uiState.value.timeDisplayValue = it.timeDisplayValue
                _uiState.value = _uiState.value.copy(
                    hours = _uiState.value.timeDisplayValue.getHoursForTimeDisplay(),
                    minutes = _uiState.value.timeDisplayValue.getMinutesForTimeDisplay(),
                    timeDisplayValue = _uiState.value.timeDisplayValue,
                    digitState = it.digitState
                )
            },
            onError = {}
        )
    }

    private suspend fun updateAppUtilityData(
        isCountdownTimerRunning: Boolean
    ) {
        appUtilityDataRepository.updateAppUtilityData(
            numberOfRunning = appUtilityData.numberOfRunning + 1,
            isCountdownTimerRunning = isCountdownTimerRunning,
            onSuccess = {},
            onError = {}
        )
    }

    private suspend fun loadAppUtilityData() {
        appUtilityDataRepository.getAppUtilityData(
            onSuccess = {
                appUtilityData.numberOfRunning = it.numberOfRunning
                appUtilityData.isCountdownTimerRunning = it.isCountdownTimerRunning
            },
            onError = {}
        )
    }
}