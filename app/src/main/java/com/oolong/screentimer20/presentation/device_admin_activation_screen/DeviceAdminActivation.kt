package com.oolong.screentimer20.presentation.device_admin_activation_screen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.screentimer20.Screen
import com.oolong.screentimer20.utils.devicePolicyManagerIntent
import com.oolong.screentimer20.utils.lockDevice

@Composable
fun LandingScreen(
    navController: NavController = rememberNavController()
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        result.data
        Log.d("LandingScreen", result.data.toString())
    }

    DefaultLandingComponent(
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
    LandingScreen()
}