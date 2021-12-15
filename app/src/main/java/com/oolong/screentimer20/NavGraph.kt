package com.oolong.screentimer20

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oolong.screentimer20.presentation.screens.SplashScreen

@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: CountdownTimerViewModel
){
    NavHost(
        navController = navController,
        startDestination = Screen.CountdownTimer.route
    ){
        composable(
            route = Screen.Splash.route
        ){
            SplashScreen()
        }
        composable(
            route = Screen.CountdownTimer.route
        ){
            CountDownScreen(
                initialArcDegree = viewModel.arcDegree,
                initialIsTimerRunning = viewModel.isTimerRunning,
                onArchDegreeChange = {
                    viewModel.arcDegree = it
                },
                onButtonClick = {
                    viewModel.isTimerRunning = it
                }
            )
        }
    }
}