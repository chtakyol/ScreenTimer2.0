package com.oolong.screentimer20

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.*
import android.app.Service
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.*

class CountDownService : Service() {

    companion object {
        const val CHANNEL_ID = "ForegroundService Kotlin"
        fun startService(
            context: Context,
            totalTime: Long,
            turnOffSound: Boolean,
            turnOffScreen: Boolean
        ) {
            val startIntent = Intent(context, CountDownService::class.java)
            startIntent.putExtra("totalTime", totalTime)
            startIntent.putExtra("turnOffScreen", turnOffScreen)
            startIntent.putExtra("turnOffSound", turnOffSound)
            ContextCompat.startForegroundService(context, startIntent)
        }

        fun stopService(context: Context) {
            val stopIntent = Intent(context, CountDownService::class.java)
            context.stopService(stopIntent)
        }
    }

    private val counterRoutine = CoroutineScope(Dispatchers.Main)

    private var builder = NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle("textTitle")
        .setContentText("textContent")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setSilent(true)

    override fun onCreate() {
        super.onCreate()
        Log.e("Lifecycles", "Service Started")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var timeInMillis = intent?.getLongExtra("totalTime", 0L)!!
        var turnOffSound = intent.getBooleanExtra("turnOffSound", true)
        var turnOffScreen = intent.getBooleanExtra("turnOffScreen", false)

        createNotificationChannel()
        startForeground(1, builder.build())

        Log.d("CountDownTimerServiceSd", turnOffSound.toString())
        Log.d("CountDownTimerServiceSc", turnOffScreen.toString())

        counterRoutine.launch {
            while(timeInMillis > 0){
                Log.d("CountDownTimerService", getReadableTime(timeInMillis = timeInMillis))
                delay(1000)
                timeInMillis -= 1000L
                builder.setContentText(getReadableTime(timeInMillis = timeInMillis))
                with(NotificationManagerCompat.from(this@CountDownService)) {
                    // notificationId is a unique int for each notification that you must define
                    notify(1, builder.build())
                }
            }
            if(turnOffSound && !turnOffScreen){
                turnOffSound()
                Log.d("CountDownService", "Music stopped!")
                CountDownService.stopService(this@CountDownService)
            } else if (!turnOffSound && turnOffScreen){
                turnOffScreen()
                Log.d("CountDownService", "Screen stopped!")
                CountDownService.stopService(this@CountDownService)
            } else if (turnOffSound && turnOffScreen){
                turnOffSound()
                turnOffScreen()
                Log.d("CountDownService", "Music and screen stopped!")
            }

            CountDownService.stopService(this@CountDownService)
        }

        val mainActivityIntent = Intent(this, MainActivity::class.java)
        val mainActivityPendingIntent: PendingIntent = getActivity(this, 0, mainActivityIntent, 0)
        builder.setContentIntent(mainActivityPendingIntent)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        counterRoutine.cancel()
        super.onDestroy()
        Log.e("Lifecycles", "Service destroyed")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager: NotificationManager? = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }


    fun turnOffScreen(){
        val deviceManger = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val compName = ComponentName(this, DeviceAdmin::class.java)
        deviceManger.lockNow()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun turnOffSound(){
        //Media Player Permission
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
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
}