package com.oolong.screentimer20.data

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.oolong.screentimer20.DurationData
import java.io.InputStream
import java.io.OutputStream

internal val Context.durationDataDataStore: DataStore<DurationData> by dataStore(
    fileName = "duration_data.pb",
    serializer = StatisticsSerializer
)

private object StatisticsSerializer: Serializer<DurationData> {
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