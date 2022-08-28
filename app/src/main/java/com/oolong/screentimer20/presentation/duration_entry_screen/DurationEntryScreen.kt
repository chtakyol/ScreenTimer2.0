package com.oolong.screentimer20.presentation.duration_entry_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.screentimer20.Screen
import com.oolong.screentimer20.presentation.components.TimeDisplay
import com.oolong.screentimer20.presentation.components.Title
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
    val backgroundColor = Color(0xFF212121)
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            title = "Screen Timer"
        )
        TimeDisplay(
            modifier = Modifier
                .padding(
                    top = 64.dp,
                ),
            hours = uiState.hours,
            minutes = uiState.minutes
        )
        Keypad(
            modifier = Modifier
                .padding(
                    top = 48.dp
                )
        ) {
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