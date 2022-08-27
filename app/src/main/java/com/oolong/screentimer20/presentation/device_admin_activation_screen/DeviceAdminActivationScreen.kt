package com.oolong.screentimer20.presentation.device_admin_activation_screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.screentimer20.Screen
import com.oolong.screentimer20.presentation.device_admin_activation_screen.components.DeviceAdminActivationComponent
import com.oolong.screentimer20.utils.devicePolicyManagerIntent
import com.oolong.screentimer20.utils.lockDevice

@Composable
fun DeviceAdminActivationScreen(
    navController: NavController = rememberNavController()
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        result.data
    }

    DeviceAdminActivationComponent(
        onGoToButtonClick = {
            navController.navigate(
                route = Screen.DurationEntryScreen.route
            )
        },
        onActivateDeviceManagerButton = {
            launcher.launch(context.devicePolicyManagerIntent())
        },
        onLockButtonClick = {
            context.lockDevice()
        }
    )
}

@Composable
@Preview
fun PreviewLandingScreen() {
    DeviceAdminActivationScreen()
}