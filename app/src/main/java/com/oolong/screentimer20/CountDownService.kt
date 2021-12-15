package com.oolong.screentimer20

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.*
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
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

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var timeInMillis = intent?.getLongExtra("totalTime", 0L)!!
        var turnOffSound = intent.getBooleanExtra("turnOffSound", true)
        var turnOffScreen = intent.getBooleanExtra("turnOffScreen", false)

        createNotificationChannel()
        startForeground(1, builder.build())

        counterRoutine.launch {
            while(true){
                Log.d("CountDownTimerService", getReadableTime(timeInMillis = timeInMillis))
                delay(1000)
                timeInMillis -= 1000L
            }
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
}