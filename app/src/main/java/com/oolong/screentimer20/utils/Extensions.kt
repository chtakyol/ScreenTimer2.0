package com.oolong.screentimer20.utils

import android.content.Context
import android.content.Intent
import com.oolong.screentimer20.services.ScreenTimerService
import com.oolong.screentimer20.utils.Constants.APP_PACKAGE_NAME

internal fun Int.getHoursForTimeDisplay(): String {
    return if (this / 100 < 10) "0${(this / 100)}" else "${(this / 100)}"
}

internal fun Int.getMinutesForTimeDisplay(): String {
    return if (this - this / 100 * 100 < 10) "0${this - this / 100 * 100}" else "${(this - this / 100 * 100)}"
}

internal fun Int.getHours(): String {
    return if (this / 60 < 10) "0${(this / 60)}" else "${(this / 100)}"
}

internal fun Int.getMinutes(): String {
    return if (this % 60< 10) "0${(this % 60)}" else "${(this % 60)}"
}

internal fun Context.startScreenTimerService(
    action: String
) {
    Intent(this, ScreenTimerService::class.java).also {
        it.action = action
        startService(it)
    }
}

internal fun Context.sendScreenTimerServiceTickBroadcast(
    durationData: Int
) {
    Intent(APP_PACKAGE_NAME).also {
        it.putExtra("Duration", durationData)
        sendBroadcast(it)
    }
}