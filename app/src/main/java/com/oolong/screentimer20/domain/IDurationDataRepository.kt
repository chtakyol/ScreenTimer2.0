package com.oolong.screentimer20.domain

interface IDurationDataRepository {

    suspend fun getDurationData(
        onSuccess: (DurationDataModel) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun updateDurationData(
        protoDurationData: Int,
        protoDigitState: Int,
        onSuccess: (isRecord: Boolean) -> Unit,
        onError: (Exception) -> Unit
    )
}