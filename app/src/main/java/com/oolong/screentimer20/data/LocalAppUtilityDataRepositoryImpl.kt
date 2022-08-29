package com.oolong.screentimer20.data

import androidx.datastore.core.DataStore
import com.oolong.screentimer20.domain.IAppUtilityDataRepository
import com.oolong.screentimer20.domain.model.AppUtilityData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class LocalAppUtilityDataRepositoryImpl(
    private val dataStore: DataStore<com.oolong.screentimer20.AppUtilityData>
): IAppUtilityDataRepository {
    override suspend fun getAppUtilityData(
        onSuccess: (AppUtilityData) -> Unit,
        onError: (Exception) -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            val appUtilityData = dataStore.data.first()

            onSuccess(
                appUtilityData.toAppUtilityData
            )
        } catch (e: Exception) {
            onError(e)
        }
    }

    override suspend fun updateAppUtilityData(
        numberOfRunning: Int,
        isCountdownTimerRunning: Boolean,
        isAppRegisteredAsDeviceAdmin: Boolean,
        onSuccess: (isRecord: Boolean) -> Unit,
        onError: (Exception) -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            dataStore.updateData { appUtilityData ->
                appUtilityData
                    .toBuilder()
                    .setNumberOfRunning(numberOfRunning)
                    .setIsCountdownTimerRunning(isCountdownTimerRunning)
                    .setIsAppRegisteredAsDeviceAdmin(isAppRegisteredAsDeviceAdmin)
                    .build()
            }
            onSuccess(true)
        } catch (e: Exception) {
            onError(e)
        }
    }

    private val com.oolong.screentimer20.AppUtilityData.toAppUtilityData: AppUtilityData
        get() {
        return AppUtilityData(
            numberOfRunning = this.numberOfRunning,
            isCountdownTimerRunning = this.isCountdownTimerRunning,
            isAppRegisteredAsDeviceAdmin = this.isAppRegisteredAsDeviceAdmin
        )
    }
}