package com.oolong.screentimer20.presentation.countdown_screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.screentimer20.presentation.components.TimeDisplay
import com.oolong.screentimer20.presentation.countdown_screen.components.CircularDurationBar
import com.oolong.screentimer20.presentation.countdown_screen.components.StopButton
import com.oolong.screentimer20.services.ScreenTimerServiceBroadcastReceiver
import com.oolong.screentimer20.utils.Constants.ACTION_START_OR_RESUME_SERVICE
import com.oolong.screentimer20.utils.getHours
import com.oolong.screentimer20.utils.getMinutes
import com.oolong.screentimer20.utils.startScreenTimerService

@Composable
fun CountdownScreen(
    context: Context,
    screenTimerServiceBroadcastReceiver: ScreenTimerServiceBroadcastReceiver,
    viewModel: CountdownScreenViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
) {

    val durationDataFromBroadcast = screenTimerServiceBroadcastReceiver.getDurationData()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularDurationBar(
//            percentage = uiState.progressBarPercentage
        )

        Column(
            modifier = Modifier
                .offset(y = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimeDisplay(
                hours = durationDataFromBroadcast.value.getHours(),
                minutes = durationDataFromBroadcast.value.getMinutes()
            )

            Spacer(modifier = Modifier.size(54.dp))

            StopButton {
                context.startScreenTimerService(ACTION_START_OR_RESUME_SERVICE)
            }
        }
    }
}

@Composable
@Preview
fun PreviewCountdownScreen() {
//    CountdownScreen()
}