package com.oolong.screentimer20.data

import androidx.datastore.core.DataStore
import com.oolong.screentimer20.DurationData
import com.oolong.screentimer20.domain.model.DurationDataModel
import com.oolong.screentimer20.domain.IDurationDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class LocalDurationDataRepositoryImpl(
    private val dataStore: DataStore<DurationData>
): IDurationDataRepository {
    override suspend fun getDurationData(
        onSuccess: (DurationDataModel) -> Unit,
        onError: (Exception) -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            val durationData = dataStore.data.first()

            onSuccess(
                durationData.toDurationDataModel
            )
        } catch (e: Exception) {
            onError(e)
        }
    }

    override suspend fun updateDurationData(
        protoDurationData: Int,
        protoDigitState: Int,
        protoDuration: Int,
        onSuccess: (isRecord: Boolean) -> Unit,
        onError: (Exception) -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            dataStore.updateData { durationData ->
                durationData
                    .toBuilder()
                    .setTimeDisplayValue(protoDurationData)
                    .setDigit(protoDigitState)
                    .setDuration(protoDuration)
                    .build()
            }
            onSuccess(true)
        } catch (e: Exception) {
            onError(e)
        }
    }

    private val DurationData.toDurationDataModel: DurationDataModel
        get() {
        return DurationDataModel(
            timeDisplayValue = this.timeDisplayValue,
            digitState = this.digit,
            duration = this.duration
        )
    }
}