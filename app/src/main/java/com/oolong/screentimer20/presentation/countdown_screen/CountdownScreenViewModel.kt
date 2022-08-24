package com.oolong.screentimer20.presentation.countdown_screen

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

    val validationState = MutableSharedFlow<CountdownScreenValidationEvent>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(100L)
            loadDurationData()
            validationState.emit(
                CountdownScreenValidationEvent.StartService
            )
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

    fun updateAppUtilityData() {
        viewModelScope.launch(Dispatchers.IO) {
            appUtilityDataRepository.updateAppUtilityData(
                numberOfRunning = 0,
                isCountdownTimerRunning = false,
                onSuccess = {},
                onError = {}
            )
        }
    }
}