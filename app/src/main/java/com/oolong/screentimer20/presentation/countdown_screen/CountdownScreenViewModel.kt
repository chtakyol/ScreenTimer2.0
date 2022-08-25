package com.oolong.screentimer20.presentation.countdown_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.screentimer20.domain.IAppUtilityDataRepository
import com.oolong.screentimer20.domain.IDurationDataRepository
import com.oolong.screentimer20.domain.model.AppUtilityData
import com.oolong.screentimer20.utils.getHoursForTimeDisplay
import com.oolong.screentimer20.utils.getMinutesForTimeDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountdownScreenViewModel @Inject constructor(
    private val durationDataRepository: IDurationDataRepository,
    private val appUtilityDataRepository: IAppUtilityDataRepository
): ViewModel() {

    private var _uiState = mutableStateOf(CountdownScreenState())
    val uiState: State<CountdownScreenState> = _uiState

    private val appUtilityData = AppUtilityData()

    val validationState = MutableSharedFlow<CountdownScreenValidationEvent>( replay = 1)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(100L)
            loadDurationData()
            loadAppUtilityData()
            validationState.emit(
                CountdownScreenValidationEvent.StartService
            )
        }
    }

    fun onEvent(event: CountdownScreenEvent) {
        when(event) {
            is CountdownScreenEvent.OnCancelButtonPressed -> {
                Log.d("CountdownScreen", "isTimerRunning: ${appUtilityData.isCountdownTimerRunning}")
                viewModelScope.launch {
                    val s = validationState.tryEmit(
                        if (appUtilityData.isCountdownTimerRunning) CountdownScreenValidationEvent.StopService else CountdownScreenValidationEvent.Idle
                    )
                    Log.d("CountdownScreen", "succ: ${s}")

                }
                updateAppUtilityData()
            }
        }
    }

    private suspend fun loadDurationData() {
        durationDataRepository.getDurationData(
            onSuccess = {
                _uiState.value.timeDisplayValue = it.timeDisplayValue
                _uiState.value = _uiState.value.copy(
                    hours = _uiState.value.timeDisplayValue.getHoursForTimeDisplay(),
                    minutes = _uiState.value.timeDisplayValue.getMinutesForTimeDisplay(),
                    timeDisplayValue = _uiState.value.timeDisplayValue,
                )
            },
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

    private fun updateAppUtilityData() {
        viewModelScope.launch(Dispatchers.IO) {
            appUtilityDataRepository.updateAppUtilityData(
                numberOfRunning = appUtilityData.numberOfRunning,
                isCountdownTimerRunning = false,
                onSuccess = {},
                onError = {}
            )
        }
    }
}