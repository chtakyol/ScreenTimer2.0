package com.oolong.screentimer20

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oolong.screentimer20.presentation.SplashScreen
import com.oolong.screentimer20.presentation.countdown_screen.CountdownScreen
import com.oolong.screentimer20.presentation.duration_entry_screen.DurationEntryScreen
import com.oolong.screentimer20.services.ScreenTimerServiceBroadcastReceiver
import com.oolong.screentimer20.ui.theme.ScreenTimer20Theme
import com.oolong.screentimer20.utils.Constants.APP_PACKAGE_NAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val screenTimerServiceBroadcastReceiver = ScreenTimerServiceBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val devicePolicyManager = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val componentName = ComponentName(this, DeviceAdmin::class.java)

        val screenTimerIntentFilter = IntentFilter(APP_PACKAGE_NAME)
        this.registerReceiver(screenTimerServiceBroadcastReceiver, screenTimerIntentFilter)

        setContent {
            val navController = rememberNavController()
            ScreenTimer20Theme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.SplashScreen.route
                ) {
                    composable(
                        route = Screen.SplashScreen.route
                    ) {
                        SplashScreen(
                            navController = navController
                        )
                    }

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
                            context = LocalContext.current,
                            screenTimerServiceBroadcastReceiver = screenTimerServiceBroadcastReceiver,
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(screenTimerServiceBroadcastReceiver)
    }

    override fun onStop() {
        super.onStop()
        try {
            unregisterReceiver(screenTimerServiceBroadcastReceiver)
        } catch (e: Exception) {
//            throw(e)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        when(requestCode){
//            100 -> {
//                if (resultCode == Activity.RESULT_OK ) {
//                    Log.d("Result", "OK")
//                    countdownTimerViewModel.deviceAdminActive.value = true
//                } else {
//                    Log.d("Result", "Na")
//                }
//
//            }
//        }
    }
}