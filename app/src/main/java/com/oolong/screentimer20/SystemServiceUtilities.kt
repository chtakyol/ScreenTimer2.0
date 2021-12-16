package com.oolong.screentimer20

import android.app.Service
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
fun stopMusic(
    audioManager: AudioManager
){
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
    devicePolicyManager.lockNow()
}