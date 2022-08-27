package com.oolong.screentimer20.presentation.device_admin_activation_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DeviceAdminActivationComponent(
    onGoToButtonClick: () -> Unit = {},
    onActivateDeviceManagerButton: () -> Unit = {},
    onLockButtonClick: () -> Unit = {}
) {
    val backgroundColor = Color(0xFF212121)

    val buttonBackgroundWhiteColor = Color(0xFFFFFFFF)
    val buttonContentWhiteColor = Color(0xFF212121)
    val buttonWhiteColor = ButtonDefaults.buttonColors(
        backgroundColor = buttonBackgroundWhiteColor,
    )

    val buttonBackgroundRedColor = Color(0xFFCD3D3D)
    val buttonContentRedColor = Color(0xFFFFFFFF)
    val buttonRedColor = ButtonDefaults.buttonColors(
        backgroundColor = buttonBackgroundRedColor,
    )
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier
                .padding(
                    top = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            text = "Activate Device Admin",
            style = MaterialTheme.typography.h2
        )

        Text(
            modifier = Modifier
                .padding(
                    top = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            text = "To provide turning off the screen " +
                    "we need to register the app as " +
                    "device admin.",
            style = MaterialTheme.typography.body1
        )

        Button(
            modifier = Modifier
                .padding(
                    top = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            colors = buttonWhiteColor,
            onClick = { onActivateDeviceManagerButton() },
        ) {
            Text(
                text = "Register this app as Device Admin",
                color = buttonContentWhiteColor
            )
        }

        Text(
            modifier = Modifier
                .padding(
                    top = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            text = "Or",
            style = MaterialTheme.typography.body1
        )

        Button(
            modifier = Modifier
                .padding(
                    top = 32.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            colors = buttonRedColor,
            onClick = { onGoToButtonClick() }
        ) {
            Text(
                text = "Go next screen",
                color = buttonContentRedColor
            )
        }

//        Button(onClick = { onLockButtonClick() }) {
//            Text(text = "Lock")
//        }
    }
}

@Composable
@Preview
fun PreviewDefaultLandingComponent() {
    DeviceAdminActivationComponent()
}