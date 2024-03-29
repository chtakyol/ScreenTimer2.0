package com.oolong.screentimer20

import android.app.admin.DevicePolicyManager
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.oolong.screentimer20.presentation.splash_screen.SplashScreen
import com.oolong.screentimer20.presentation.countdown_screen.CountdownScreen
import com.oolong.screentimer20.presentation.duration_entry_screen.DurationEntryScreen
import com.oolong.screentimer20.presentation.device_admin_activation_screen.DeviceAdminActivationScreen
import com.oolong.screentimer20.presentation.onboarding_screen.OnboardingScreen
import com.oolong.screentimer20.services.ScreenTimerServiceBroadcastReceiver
import com.oolong.screentimer20.ui.theme.ScreenTimer20Theme
import com.oolong.screentimer20.utils.Constants.APP_PACKAGE_NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val screenTimerServiceBroadcastReceiver = ScreenTimerServiceBroadcastReceiver()

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                        route = Screen.OnboardingScreen.route
                    ) {
                        OnboardingScreen(
                            navController = navController
                        )
                    }

                    composable(
                        route = Screen.DeviceAdminActivationScreen.route
                    ) {
                        DeviceAdminActivationScreen(
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

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(screenTimerServiceBroadcastReceiver)
    }
}