package com.oolong.screentimer20

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.media.AudioManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oolong.screentimer20.presentation.screens.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: CountdownTimerViewModel,
    audioManager: AudioManager,
    devicePolicyManager: DevicePolicyManager,
    componentName: ComponentName,
    activity: MainActivity,
){

    NavHost(
        navController = navController,
        startDestination = Screen.CountdownTimer.route
    ){
        composable(
            route = Screen.Splash.route
        ){
            SplashScreen(navController = navController)
        }
        composable(
            route = Screen.CountdownTimer.route
        ){
            CountDownScreen(
                viewModel = viewModel,
                initialArcDegree = viewModel.arcDegree,
                initialIsTimerRunning = viewModel.isTimerRunning.value,
                initialSoundOff = viewModel.soundOff.value,
                initialScreenOff = viewModel.deviceAdminActive.value,
                onArchDegreeChange = {
                    viewModel.arcDegree = it

                },
                onButtonClick = {
                    if (viewModel.soundOff.value && !viewModel.screenOff.value){
                        viewModel.isTimerRunning.value = it
                    } else if (viewModel.soundOff.value && viewModel.screenOff.value){
                        if(viewModel.deviceAdminActive.value){
                            viewModel.isTimerRunning.value = it
                        } else {
                            askForDeviceAdminPermission(
                                devicePolicyManager =devicePolicyManager,
                                activity = activity,
                                componentName = componentName
                            )

                        }
                    } else if (!viewModel.soundOff.value && viewModel.screenOff.value){
                        if(devicePolicyManager.isAdminActive(componentName)){
                            viewModel.isTimerRunning.value = it
                        } else {
                            askForDeviceAdminPermission(
                                devicePolicyManager =devicePolicyManager,
                                activity = activity,
                                componentName = componentName
                            )
                        }
                    }
                },
                onSoundOffSwitchButtonClick = {
                    viewModel.soundOff.value = it
                },
                onScreenOffSwitchButtonClick = {
                    if (viewModel.screenOff.value){
                        removeActiveAdmin(
                            devicePolicyManager = devicePolicyManager,
                            componentName = componentName
                        )
                    }
                    viewModel.screenOff.value = it
                },
                onTick = {
                    if (it <= 0.086){
                        if(viewModel.soundOff.value && !viewModel.screenOff.value){
                            stopMusic(audioManager = audioManager)
                        } else if (!viewModel.soundOff.value && viewModel.screenOff.value){
                            lockDevice(devicePolicyManager = devicePolicyManager, componentName = componentName)
                        } else if (viewModel.soundOff.value && viewModel.screenOff.value){
                            lockDevice(devicePolicyManager = devicePolicyManager, componentName = componentName)
                            stopMusic(audioManager = audioManager)
                        }
                        viewModel.isTimerRunning.value = false
                    } else {
                        viewModel.arcDegree = it
                    }
                }
            )
        }
    }
}