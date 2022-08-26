package com.oolong.screentimer20.utils

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Context.DEVICE_POLICY_SERVICE
import android.content.Intent
import android.os.Parcel
import androidx.activity.ComponentActivity
import com.oolong.screentimer20.R
import com.oolong.screentimer20.device_administration.ScreenTimerDeviceAdminReceiver
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

internal fun Context.stopScreenTimerService(
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

internal fun Context.getComponentName(): ComponentName {
    return ComponentName(this, ScreenTimerDeviceAdminReceiver::class.java)
}

internal fun Context.devicePolicyManagerIntent(): Intent {
    val componentName = this.getComponentName()
    return Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).also {
        it.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
        it.putExtra(
            DevicePolicyManager.EXTRA_ADD_EXPLANATION,
            R.string.device_manager_explanation
        )
    }
}

internal fun Context.lockDevice() {
    val devicePolicyManager = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
    if (devicePolicyManager.isAdminActive(this.getComponentName())) devicePolicyManager.lockNow()
}