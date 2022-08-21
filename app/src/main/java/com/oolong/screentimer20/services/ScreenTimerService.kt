package com.oolong.screentimer20.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.oolong.screentimer20.domain.IDurationDataRepository
import com.oolong.screentimer20.utils.Constants.ACTION_ADD_BUTTON
import com.oolong.screentimer20.utils.Constants.ACTION_PAUSE_SERVICE
import com.oolong.screentimer20.utils.Constants.ACTION_START_OR_RESUME_SERVICE
import com.oolong.screentimer20.utils.Constants.ACTION_STOP_SERVICE
import com.oolong.screentimer20.utils.sendScreenTimerServiceTickBroadcast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ScreenTimerService: Service() {

    @Inject
    lateinit var durationDataRepository: IDurationDataRepository

    private lateinit var countDownTimer: CountDownTimer

    private var timeDisplayValue = 0 // This design mistake
    private var digitState = 0 // This design mistake
    private var duration = 0 // This values as min

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        Log.d("ScreenTimerService", "Screen Timer Service Start")
                        configureTimerValues()
                        startTimer()
                    }
                }
                ACTION_PAUSE_SERVICE -> {
                    Log.d("ScreenTimerService", "Screen Timer Service Pause")
                }
                ACTION_STOP_SERVICE -> {
                    Log.d("ScreenTimerService", "Screen Timer Service Stop")
                }
                ACTION_ADD_BUTTON -> {
                }
                else -> {
                    Log.d("ScreenTimerService", "Unexpected Command")
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private suspend fun configureTimerValues() {
        durationDataRepository.getDurationData(
            onSuccess = {
                timeDisplayValue = it.timeDisplayValue
                digitState = it.digitState
                duration = it.duration
            },
            onError = {}
        )
    }

    private fun startTimer() {
        countDownTimer = object: CountDownTimer( duration * 60000L, 1 * 1000) {
            override fun onTick(p0: Long) {
                Log.d("ScreenTimerService", "Duration: $duration")
                duration -= 1
                CoroutineScope(Dispatchers.Main).launch {
                    durationDataRepository.updateDurationData(
                        protoDurationData = timeDisplayValue,
                        protoDigitState = digitState,
                        protoDuration = duration,
                        onSuccess = {},
                        onError = {}
                    )
                }
                sendScreenTimerServiceTickBroadcast(duration)
                if (duration == 0) countDownTimer.cancel()
            }

            override fun onFinish() {
                stopSelf()
            }
        }

        countDownTimer.start()
    }

    override fun onDestroy() {
        // TODO why this function doesn't call with stopSelf() method?
        Log.d("ScreenTimerService", "Service destroyed!")
        super.onDestroy()
    }
}