package com.oolong.screentimer20.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SplashScreen(){
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ){
        Icon(
            modifier = Modifier
                .size(120.dp),
            imageVector = Icons.Default.Email,
            contentDescription = "Splash screen icon",
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen(){
    SplashScreen()
}