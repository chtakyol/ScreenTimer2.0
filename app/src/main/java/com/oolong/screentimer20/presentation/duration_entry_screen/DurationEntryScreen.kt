package com.oolong.screentimer20.presentation.duration_entry_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.screentimer20.presentation.components.TimeDisplay
import com.oolong.screentimer20.presentation.duration_entry_screen.components.Keypad

@Composable
fun DurationEntryScreen(

) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimeDisplay()
        Spacer(modifier = Modifier.size(48.dp))
        Keypad()
    }
}

@Composable
@Preview
fun PreviewDurationEntryScreen() {
    DurationEntryScreen()
}