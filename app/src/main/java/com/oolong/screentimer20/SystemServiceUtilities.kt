package com.oolong.screentimer20

import android.app.Service
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Build
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
fun stopMusic(
    audioManager: AudioManager
){
    Log.d("SysSerUtils", "Audio stop!")
    when {
        Build.VERSION.SDK_INT <= 25 -> {
            audioManager.requestAudioFocus(
                AudioManager.OnAudioFocusChangeListener { 1 },
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN
            )
        }
        (Build.VERSION.SDK_INT > 25) and (Build.VERSION.SDK_INT <= 30) -> {
            val focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN).run {
                setAudioAttributes(AudioAttributes.Builder().run {
                    setUsage(AudioAttributes.USAGE_GAME)
                    setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    build()
                })
                build()
            }

            val res = audioManager.requestAudioFocus(focusRequest)

            Log.d("Audio", res.toString())

        }
        Build.VERSION.SDK_INT > 30 -> {

        }
    }
}

fun lockDevice(
    devicePolicyManager: DevicePolicyManager,
    componentName: ComponentName
){
    Log.d("SysSerUtils", "Screen stop!")

    devicePolicyManager.lockNow()
}

fun askForDeviceAdminPermission(
    devicePolicyManager: DevicePolicyManager,
    activity: MainActivity,
    componentName: ComponentName
){
    val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
    intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
    intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "You should enable the app!")
    activity.startActivityForResult(intent, 100)
}

fun removeActiveAdmin(
    devicePolicyManager: DevicePolicyManager,
    componentName: ComponentName
){
    devicePolicyManager.removeActiveAdmin(componentName)
}