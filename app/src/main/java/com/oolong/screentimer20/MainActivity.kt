package com.oolong.screentimer20

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oolong.screentimer20.presentation.countdown_screen.CountdownScreen
import com.oolong.screentimer20.presentation.duration_entry_screen.DurationEntryScreen
import com.oolong.screentimer20.ui.theme.ScreenTimer20Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val TIMER_VALUES = "timer_values"
    private val countdownTimerViewModel = CountdownTimerViewModel()

    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lateinit var navController: NavHostController
        Log.d("Lifecycles", "onCreateCalled")

        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        val devicePolicyManager = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val componentName = ComponentName(this, DeviceAdmin::class.java)

        countdownTimerViewModel.deviceAdminActive.value = devicePolicyManager.isAdminActive(componentName)

        setContent {
            ScreenTimer20Theme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.DurationEntryScreen.route
                ) {
                    composable(
                        route = Screen.DurationEntryScreen.route
                    ) {
                        DurationEntryScreen(
                            navController = navController
                        )
                    }

                    composable(
                        route = Screen.CountdownScreen.route
                    ) {
                        CountdownScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            100 -> {
                if (resultCode == Activity.RESULT_OK ) {
                    Log.d("Result", "OK")
                    countdownTimerViewModel.deviceAdminActive.value = true
                } else {
                    Log.d("Result", "Na")
                }

            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycles", "onStartCalled")
        CountDownService.stopService(this)
        countdownTimerViewModel.soundOff.value = getTurnOffSound()
        countdownTimerViewModel.screenOff.value = getTurnOffScreen()
        countdownTimerViewModel.arcDegree = millisToDegree(getTimeInMillis().toFloat())
        countdownTimerViewModel.isTimerRunning.value = getIsRunning()

        Log.d("onStart", countdownTimerViewModel.soundOff.value.toString())
        Log.d("onStart", countdownTimerViewModel.screenOff.value.toString())
        Log.d("onStart", countdownTimerViewModel.isTimerRunning.value.toString())
        Log.d("onStart", getReadableTime(degreeToMillis(countdownTimerViewModel.arcDegree)))
        Log.d("onStart", countdownTimerViewModel.intendedArcDegree.toString())

        val closedTimeInMillis = getTimeInMillis() - calculateElapsedTimeInMillis()

        if (closedTimeInMillis <= 0){
            countdownTimerViewModel.isTimerRunning.value = false
            countdownTimerViewModel.arcDegree = millisToDegree(getTimeInMillis().toFloat())
        } else {
            countdownTimerViewModel.arcDegree = if (countdownTimerViewModel.isTimerRunning.value) {
                millisToDegree(closedTimeInMillis.toFloat())
            } else {
                getTimeInMillis().toFloat()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycles", "onStopCalled")
        Log.d("onStop", countdownTimerViewModel.soundOff.value.toString())
        Log.d("onStop", countdownTimerViewModel.screenOff.value.toString())
        Log.d("onStop", countdownTimerViewModel.isTimerRunning.value.toString())
        Log.d("onStop", getReadableTime(degreeToMillis(countdownTimerViewModel.arcDegree)))
        Log.d("onStop", countdownTimerViewModel.intendedArcDegree.toString())

        saveCountdownTimerValues(
            turnOffSound = countdownTimerViewModel.soundOff.value,
            turnOffScreen= countdownTimerViewModel.screenOff.value,
            isTimerRunning = countdownTimerViewModel.isTimerRunning.value,
            timeInMillis = degreeToMillis(countdownTimerViewModel.arcDegree),
            intendedArcDegree = countdownTimerViewModel.intendedArcDegree
        )

        if(countdownTimerViewModel.isTimerRunning.value){
            saveSystemTimeInMillis()
            CountDownService.startService(
                context = this,
                totalTime = degreeToMillis(countdownTimerViewModel.arcDegree),
                turnOffSound = countdownTimerViewModel.soundOff.value,
                turnOffScreen = countdownTimerViewModel.screenOff.value
            )
        }
    }

    private fun saveSystemTimeInMillis(){
        // For saving system time
        val sharedPreference =  getSharedPreferences(TIMER_VALUES, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putLong("systemTimeInMillisOnDestroy", System.currentTimeMillis())
        editor.apply()
    }

    private fun getSavedSystemTimeInMillis(): Long {
        val sharedPreference = getSharedPreferences(TIMER_VALUES, Context.MODE_PRIVATE)
        return sharedPreference.getLong("systemTimeInMillisOnDestroy", 0L)
    }

    private fun saveCountdownTimerValues(
        turnOffSound: Boolean,
        turnOffScreen: Boolean,
        isTimerRunning: Boolean,
        timeInMillis: Long,
        intendedArcDegree: Float
    ){
        val sharedPreference =  getSharedPreferences(TIMER_VALUES,Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putBoolean("turnOffSound", turnOffSound)
        editor.putBoolean("turnOffScreen", turnOffScreen)
        editor.putBoolean("isTimerRunning", isTimerRunning)
        editor.putLong("timeInMillis", timeInMillis)
        editor.putFloat("intendedArcDegree", intendedArcDegree)
        editor.apply()
    }

    private fun getIntendedArcDegree(): Float {
        val sharedPreferences = getSharedPreferences(TIMER_VALUES, Context.MODE_PRIVATE)
        return sharedPreferences.getFloat("intendedArcDegree", 0f)
    }

    private fun getTurnOffScreen(): Boolean {
        val sharedPreference = getSharedPreferences(TIMER_VALUES, Context.MODE_PRIVATE)
        return sharedPreference.getBoolean("turnOffScreen", false)
    }

    private fun getTurnOffSound(): Boolean {
        val sharedPreference = getSharedPreferences(TIMER_VALUES, Context.MODE_PRIVATE)
        return sharedPreference.getBoolean("turnOffSound", true)
    }

    private fun getIsRunning(): Boolean{
        val sharedPreference = getSharedPreferences(TIMER_VALUES, Context.MODE_PRIVATE)
        return sharedPreference.getBoolean("isTimerRunning", false)
    }

    private fun getTimeInMillis(): Long{
        val sharedPreferences = getSharedPreferences(TIMER_VALUES, Context.MODE_PRIVATE)
        return sharedPreferences.getLong("timeInMillis", 0L)
    }

    private fun calculateElapsedTimeInMillis(): Long{
        return System.currentTimeMillis() - getSavedSystemTimeInMillis()
    }
}