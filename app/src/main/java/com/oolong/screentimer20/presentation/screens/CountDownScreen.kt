package com.oolong.screentimer20

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
import kotlin.math.absoluteValue

@ExperimentalComposeUiApi
@Composable
fun CountDownScreen(
    initialArcDegree: Float = 15f,
    initialIsTimerRunning: Boolean = false,
    onArchDegreeChange: (Float) -> Unit, // this will expose the arc degree to main activity
    onButtonClick: (Boolean) -> Unit // this will expose the is timer running to main activity
){
//    var arcDegree by remember { mutableStateOf(initialArcDegree) }
//    var isTimerRunning by remember { mutableStateOf(initialIsTimerRunning) }
    var arcDegree by remember { mutableStateOf(initialArcDegree) }
    var isTimerRunning by remember { mutableStateOf(initialIsTimerRunning) }
    CountDownScreenComp(
        arcDegree = arcDegree,
        isTimerRunning = isTimerRunning,
        onArcDegreeChange = {
            arcDegree = it
            onArchDegreeChange(arcDegree) // exposing
        },
        onButtonClick = {
            isTimerRunning = it
            onButtonClick(isTimerRunning)
        },
        onTick = {
            arcDegree = it
        }
    )
}

@ExperimentalComposeUiApi
@Composable
fun CountDownScreenComp(
    arcDegree: Float = 90f,
    isTimerRunning: Boolean = false,
    onArcDegreeChange: (Float) -> Unit,
    onButtonClick: (Boolean) -> Unit,
    onTick: (Float) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SwitchButtonWithText(
            text = "Turn off sound",
            initialStatus = true
        ){

        }

        SwitchButtonWithText(
            text = "Turn off screen",
            initialStatus = false
        ){
            // TODO control start button active state.
        }

        Divider()

        Box(
            contentAlignment = Alignment.Center
        ){
            CircularSlider(
                indicatorValue = arcDegree,
                isTimerRunning = isTimerRunning,
            ){
//                arcDegree = it
                onArcDegreeChange(it)
            }
            DurationText(
                hourVal = getHours(degreeToMillis(arcDegree)),
                minuteVal = getMinutes(degreeToMillis(arcDegree)),
                secondVal = getSeconds(degreeToMillis(arcDegree))
            )
        }

        Text(text = arcDegree.toString())

        Button(
            onClick = {
//            isTimerRunning = !isTimerRunning
                onButtonClick(!isTimerRunning)
        }) {
            if (isTimerRunning) {
                LaunchedEffect(key1 = arcDegree){
                    delay(100)
//                    arcDegree -= millisToDegree(1000f)
                    onTick(arcDegree - millisToDegree(1000f))
                }
            }

            Text(text = if(isTimerRunning) "Stop" else "Start")
        }

    }
}

@ExperimentalComposeUiApi
@Composable
@Preview(showBackground = true)
fun CountDownScreenPreview(){
    CountDownScreen(
        onArchDegreeChange = {

        },
        onButtonClick = {

        }
    )
}