package com.oolong.screentimer20.presentation.splash_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.screentimer20.domain.IAppUtilityDataRepository
import com.oolong.screentimer20.domain.model.AppUtilityData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val appUtilityDataRepository: IAppUtilityDataRepository
): ViewModel(){

    var appUtilityData = AppUtilityData()

    val splashScreenNavigationEvent = MutableSharedFlow<SplashScreenNavigationEvent>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(200L)
            loadAppUtilityData()
            Log.d("SplashScreen", appUtilityData.isCountdownTimerRunning.toString())
            if (appUtilityData.numberOfRunning == 0) {
                splashScreenNavigationEvent.emit(SplashScreenNavigationEvent.NavigateToOnboardingScreen)
            } else {
                if (appUtilityData.isCountdownTimerRunning) {
                    splashScreenNavigationEvent.emit(SplashScreenNavigationEvent.NavigateToCountdownScreen)
                } else {
                    splashScreenNavigationEvent.emit(SplashScreenNavigationEvent.NavigateToDurationEntryScreen)
                }
            }
        }
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