package com.oolong.screentimer20

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.screentimer20.ui.theme.ScreenTimer20Theme
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
    var enableState by remember {mutableStateOf(true) }

    CountDownScreenComp(
        arcDegree = arcDegree,
        isTimerRunning = isTimerRunning,
        switchButtonStatus = enableState,
        initialSoundOff = soundOff,
        initialScreenOff = screenOff,
        isDeviceAdminActive = isDeviceAdminActive.value,
        onArcDegreeChange = {
            arcDegree = it
            onArchDegreeChange(arcDegree) // exposing
        },
        onTouchDone = {
            viewModel.intendedArcDegree = it
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
            enableState = !it
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
    switchButtonStatus: Boolean = true,
    initialSoundOff: Boolean = true,
    initialScreenOff: Boolean = true,
    isDeviceAdminActive: Boolean = true,
    onArcDegreeChange: (Float) -> Unit,
    onTouchDone: (Float) -> Unit,
    onButtonClick: (Boolean) -> Unit,
    onTick: (Float) -> Unit,
    onSoundOffSwitchButtonClick: (Boolean) -> Unit,
    onScreenOffSwitchButtonClick: (Boolean) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(8.dp)
            .offset(y=32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SwitchButtonWithText(
            text = stringResource(id = R.string.sound_switch_text),
            initialStatus = initialSoundOff,
            enabled = switchButtonStatus
        ){
            onSoundOffSwitchButtonClick(it)
        }

        SwitchButtonWithText(
            text = stringResource(id = R.string.screen_switch_text),
            initialStatus = initialScreenOff,
            enabled = switchButtonStatus
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
                onValueChange = {
                    onArcDegreeChange(it)
                },
                onTouchDone = {
                    onTouchDone(it)
                }
            )
            DurationText(
                hourVal = getHours(degreeToMillis(arcDegree)),
                minuteVal = getMinutes(degreeToMillis(arcDegree)),
                secondVal = getSeconds(degreeToMillis(arcDegree))
            )
        }

        Divider()

        Button(
            modifier = Modifier.padding(16.dp),
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
                Text(text = if (isTimerRunning) stringResource(id = R.string.stop) else stringResource(id = R.string.start))
            } else if (initialSoundOff && initialScreenOff){
                if (isDeviceAdminActive){
                    Text(text = if (isTimerRunning) stringResource(id = R.string.stop) else stringResource(id = R.string.start))
                } else {
                    Text(text = stringResource(id = R.string.activate_device_admin_button_text))
                }
            } else if (!initialSoundOff && initialScreenOff){
                if (isDeviceAdminActive){
                    Text(text = if (isTimerRunning) stringResource(id = R.string.stop) else stringResource(id = R.string.start))
                } else {
                    Text(text = stringResource(id = R.string.activate_device_admin_button_text))
                }
            } else if (!initialSoundOff && !initialScreenOff) {
                Text(text = stringResource(id = R.string.passive_button_text))
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
@Preview(showBackground = true)
fun CountDownScreenPreview(){
    ScreenTimer20Theme() {
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
}