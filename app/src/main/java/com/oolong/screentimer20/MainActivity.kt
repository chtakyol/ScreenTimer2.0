package com.oolong.screentimer20

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.navigation.compose.rememberNavController
import com.oolong.screentimer20.ui.theme.ScreenTimer20Theme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    private val TIMER_VALUES = "timer_values"
    private val countdownTimerViewModel = CountdownTimerViewModel()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var navController: NavHostController
        Log.d("Lifecycles", "onCreateCalled")

        setContent {
            ScreenTimer20Theme() {
                navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    viewModel = countdownTimerViewModel
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycles", "onStartCalled")
        CountDownService.stopService(this)
        countdownTimerViewModel.isTimerRunning = getIsRunning()
        val initialTimeInMillis = if (countdownTimerViewModel.isTimerRunning) getTimeInMillis() - calculateElapsedTimeInMillis() else getTimeInMillis()
        countdownTimerViewModel.arcDegree = millisToDegree(initialTimeInMillis.toFloat())

    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycles", "onStopCalled")
        Log.d("Lifecycles", countdownTimerViewModel.arcDegree.toString())

        saveCountdownTimerValues(
            isTimerRunning = countdownTimerViewModel.isTimerRunning,
            timeInMillis = degreeToMillis(countdownTimerViewModel.arcDegree)
        )

        if(countdownTimerViewModel.isTimerRunning){
            saveSystemTimeInMillis()

            CountDownService.startService(
                context = this,
                totalTime = degreeToMillis(countdownTimerViewModel.arcDegree),
                turnOffSound = false,
                turnOffScreen = false
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
        isTimerRunning: Boolean,
        timeInMillis: Long
    ){
        val sharedPreference =  getSharedPreferences(TIMER_VALUES,Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putBoolean("isTimerRunning", isTimerRunning)
        editor.putLong("timeInMillis", timeInMillis)
        editor.apply()
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