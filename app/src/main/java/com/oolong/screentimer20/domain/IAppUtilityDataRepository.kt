package com.oolong.screentimer20.domain

import com.oolong.screentimer20.domain.model.AppUtilityData

interface IAppUtilityDataRepository {
    suspend fun getAppUtilityData(
        onSuccess: (AppUtilityData) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun updateAppUtilityData(
        numberOfRunning: Int,
        isCountdownTimerIsRunning: Boolean,
        onSuccess: (isRecord: Boolean) -> Unit,
        onError: (Exception) -> Unit
    )
}