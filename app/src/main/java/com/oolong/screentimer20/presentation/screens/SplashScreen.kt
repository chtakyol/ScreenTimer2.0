package com.oolong.screentimer20.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.oolong.screentimer20.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
){
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier
                .size(120.dp),
            imageVector = Icons.Default.Email,
            contentDescription = "Splash screen icon",
            tint = Color.White
        )
    }
    val wait = true
    LaunchedEffect(key1 = wait){
        delay(2000)
        navController.navigate(Screen.CountdownTimer.route)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen(){
//    SplashScreen(navController = NavController)
}