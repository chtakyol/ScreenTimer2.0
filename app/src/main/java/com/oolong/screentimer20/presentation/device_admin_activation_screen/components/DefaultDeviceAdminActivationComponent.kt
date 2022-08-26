package com.oolong.screentimer20.presentation.device_admin_activation_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DefaultLandingComponent(
    onGoToButtonClick: () -> Unit = {},
    onActivateDeviceManagerButton: () -> Unit = {},
    onLockButtonClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Default Landing")
        Button(onClick = { onGoToButtonClick() }) {
            Text(text = "Go next screen")
        }
        Button(onClick = { onActivateDeviceManagerButton() }) {
            Text(text = "Activate Device Manager")
        }
        Button(onClick = { onLockButtonClick() }) {
            Text(text = "Lock")
        }
    }
}

@Composable
@Preview
fun PreviewDefaultLandingComponent() {
    DefaultLandingComponent()
}