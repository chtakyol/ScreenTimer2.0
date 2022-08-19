package com.oolong.screentimer20.presentation.countdown_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.screentimer20.presentation.components.TimeDisplay
import com.oolong.screentimer20.presentation.countdown_screen.components.CircularDurationBar
import com.oolong.screentimer20.presentation.countdown_screen.components.StopButton
import com.oolong.screentimer20.presentation.duration_entry_screen.components.SingleKey

@Composable
fun CountdownScreen(
    navController: NavController = rememberNavController()
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularDurationBar()

        Column(
            modifier = Modifier
                .offset(y = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimeDisplay()

            Spacer(modifier = Modifier.size(54.dp))

            StopButton {

            }
        }
    }
}

@Composable
@Preview
fun PreviewCountdownScreen() {
    CountdownScreen()
}