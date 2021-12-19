package com.oolong.screentimer20

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@ExperimentalComposeUiApi
@Composable
fun CountDownScreen(
    viewModel: CountdownTimerViewModel,
    initialArcDegree: Float = 15f,
    initialIsTimerRunning: Boolean = false,
    initialSoundOff: Boolean = true,
    initialScreenOff: Boolean = false,
    onArchDegreeChange: (Float) -> Unit, // this will expose the arc degree to main activity
    onButtonClick: (Boolean) -> Unit, // this will expose the is timer running to main activity
    onSoundOffSwitchButtonClick: (Boolean) -> Unit,
    onScreenOffSwitchButtonClick: (Boolean) -> Unit,
    onTick: (Float) -> Unit
){
    var arcDegree by remember { mutableStateOf(initialArcDegree) }
    var isTimerRunning by remember { mutableStateOf(initialIsTimerRunning) }
    var soundOff by remember { mutableStateOf(initialSoundOff) }
    var screenOff by remember { mutableStateOf(initialScreenOff) }
    val isDeviceAdminActive by remember { mutableStateOf(viewModel.deviceAdminActive) }

    CountDownScreenComp(
        arcDegree = arcDegree,
        isTimerRunning = isTimerRunning,
        initialSoundOff = soundOff,
        initialScreenOff = screenOff,
        isDeviceAdminActive = isDeviceAdminActive.value,
        onArcDegreeChange = {
            arcDegree = it
            onArchDegreeChange(arcDegree) // exposing
        },
        onSoundOffSwitchButtonClick = {
            soundOff = it
            onSoundOffSwitchButtonClick(soundOff)
        },
        onScreenOffSwitchButtonClick = {
            screenOff = it
            onScreenOffSwitchButtonClick(screenOff)
        },
        onButtonClick = {
            isTimerRunning = it
            onButtonClick(isTimerRunning)
        },
        onTick = {
            if (it > 0){
                arcDegree = it
                onTick(it)
            } else {
                isTimerRunning = false
                arcDegree = initialArcDegree
                viewModel.isTimerRunning.value = false
            }
        }
    )
}

@ExperimentalComposeUiApi
@Composable
fun CountDownScreenComp(
    arcDegree: Float = 90f,
    isTimerRunning: Boolean = false,
    initialSoundOff: Boolean = true,
    initialScreenOff: Boolean = true,
    isDeviceAdminActive: Boolean = true,
    onArcDegreeChange: (Float) -> Unit,
    onButtonClick: (Boolean) -> Unit,
    onTick: (Float) -> Unit,
    onSoundOffSwitchButtonClick: (Boolean) -> Unit,
    onScreenOffSwitchButtonClick: (Boolean) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SwitchButtonWithText(
            text = "Turn off sound",
            initialStatus = initialSoundOff
        ){
            onSoundOffSwitchButtonClick(it)
        }

        SwitchButtonWithText(
            text = "Turn off screen",
            initialStatus = initialScreenOff
        ){
            onScreenOffSwitchButtonClick(it)
        }

        Divider()

        Box(
            contentAlignment = Alignment.Center
        ){
            CircularSlider(
                indicatorValue = arcDegree,
                isTimerRunning = isTimerRunning,
            ){
                onArcDegreeChange(it)
            }
            DurationText(
                hourVal = getHours(degreeToMillis(arcDegree)),
                minuteVal = getMinutes(degreeToMillis(arcDegree)),
                secondVal = getSeconds(degreeToMillis(arcDegree))
            )
        }

//        Text(text = arcDegree.toString())

        Button(
            enabled = !(!initialSoundOff && !initialScreenOff),
            onClick = {
                if (initialScreenOff) {
                    if (isDeviceAdminActive) {
                        onButtonClick(!isTimerRunning)
                    } else {
                        onButtonClick(isTimerRunning)
                    }
                } else {
                    onButtonClick(!isTimerRunning)
                }
        }) {
            if (isTimerRunning) {
                LaunchedEffect(key1 = arcDegree){
                    delay(100)
                    onTick(arcDegree - millisToDegree(1000f))
                }
            }

            if (initialSoundOff && !initialScreenOff){
                Text(text = if (isTimerRunning) "Stop" else "Start")
            } else if (initialSoundOff && initialScreenOff){
                if (isDeviceAdminActive){
                    Text(text = if (isTimerRunning) "Stop" else "Start")
                } else {
                    Text(text = "Activate device admin!")
                }
            } else if (!initialSoundOff && initialScreenOff){
                if (isDeviceAdminActive){
                    Text(text = if (isTimerRunning) "Stop" else "Start")
                } else {
                    Text(text = "Activate device admin!")
                }
            } else if (!initialSoundOff && !initialScreenOff) {
                Text(text = "Enable one feature")
            }
        }

    }
}

@ExperimentalComposeUiApi
@Composable
@Preview(showBackground = true)
fun CountDownScreenPreview(){
    CountDownScreen(
        viewModel = CountdownTimerViewModel(),
        onArchDegreeChange = {

        },
        onButtonClick = {

        },
        onSoundOffSwitchButtonClick = {

        },
        onScreenOffSwitchButtonClick = {

        },
        onTick = {

        }
    )
}