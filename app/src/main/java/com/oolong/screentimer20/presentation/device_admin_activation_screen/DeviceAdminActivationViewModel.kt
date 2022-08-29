package com.oolong.screentimer20.presentation.device_admin_activation_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.screentimer20.domain.IAppUtilityDataRepository
import com.oolong.screentimer20.domain.model.AppUtilityData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceAdminActivationViewModel @Inject constructor(
    private val appUtilityDataRepository: IAppUtilityDataRepository
): ViewModel() {

    val appUtilityData = AppUtilityData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadAppUtilityData()
        }
    }

    private suspend fun loadAppUtilityData() {
        appUtilityDataRepository.getAppUtilityData(
            onSuccess = {
                appUtilityData.numberOfRunning = it.numberOfRunning
                appUtilityData.isCountdownTimerRunning = it.isCountdownTimerRunning
                appUtilityData.isAppRegisteredAsDeviceAdmin = it.isAppRegisteredAsDeviceAdmin
            },
            onError = {}
        )
    }

    private suspend fun updateAppUtilityData() {
        appUtilityDataRepository.updateAppUtilityData(
            numberOfRunning = appUtilityData.numberOfRunning,
            isCountdownTimerRunning = false,
            isAppRegisteredAsDeviceAdmin = true,
            onSuccess = {},
            onError = {}
        )
    }

    fun onDeviceAdminRegistrationComplete() {
        viewModelScope.launch(Dispatchers.IO) {
            updateAppUtilityData()
        }
    }
}