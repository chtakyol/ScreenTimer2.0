package com.oolong.screentimer20.data

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.oolong.screentimer20.AppUtilityData
import com.oolong.screentimer20.DurationData
import java.io.InputStream
import java.io.OutputStream

internal val Context.durationDataDataStore: DataStore<DurationData> by dataStore(
    fileName = "duration_data.pb",
    serializer = DurationDataSerializer
)

private object DurationDataSerializer: Serializer<DurationData> {
    override val defaultValue: DurationData = DurationData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): DurationData {
        try {
            return DurationData.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: DurationData, output: OutputStream) = t.writeTo(output)
}

internal val Context.appUtilityData: DataStore<AppUtilityData> by dataStore(
    fileName = "app_utility_data.pb",
    serializer = AppUtilityDataSerializer
)

private object AppUtilityDataSerializer: Serializer<AppUtilityData> {
    override val defaultValue: AppUtilityData = AppUtilityData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AppUtilityData {
        try {
            return AppUtilityData.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: AppUtilityData, output: OutputStream) = t.writeTo(output)
}
