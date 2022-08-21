package com.oolong.screentimer20.presentation.duration_entry_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.screentimer20.Screen
import com.oolong.screentimer20.presentation.components.TimeDisplay
import com.oolong.screentimer20.presentation.duration_entry_screen.components.Keypad

@Composable
fun DurationEntryScreen(
    viewModel: DurationEntryScreenViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
) {
    val uiState = viewModel.uiState.value
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationState.collect { event ->
            when(event) {
                is DurationScreenValidationEvent.Success -> {
                    Toast
                        .makeText(context, "Cool!", Toast.LENGTH_SHORT)
                        .show()
                    navController.navigate(
                        route = Screen.CountdownScreen.route
                    )
                }
            }
        }
    }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimeDisplay(
            hours = uiState.hours,
            minutes = uiState.minutes
        )
        Spacer(modifier = Modifier.size(48.dp))
        Keypad {
            viewModel.onEvent(
                DurationEntryScreenEvent.OnKeypadPressed(it)
            )
        }
    }
}

@Composable
@Preview
fun PreviewDurationEntryScreen() {
    DurationEntryScreen()
}