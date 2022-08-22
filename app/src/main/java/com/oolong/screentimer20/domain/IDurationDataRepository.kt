package com.oolong.screentimer20.domain

import com.oolong.screentimer20.domain.model.DurationDataModel

interface IDurationDataRepository {

    suspend fun getDurationData(
        onSuccess: (DurationDataModel) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun updateDurationData(
        protoDurationData: Int,
        protoDigitState: Int,
        protoDuration: Int,
        onSuccess: (isRecord: Boolean) -> Unit,
        onError: (Exception) -> Unit
    )
}