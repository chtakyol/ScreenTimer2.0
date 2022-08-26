package com.oolong.screentimer20.presentation.onboarding_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.oolong.screentimer20.presentation.onboarding_screen.OnboardingPage

@Composable
fun PagerScreen(
    onboardingPage: OnboardingPage
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
//        Image(
//            modifier = Modifier
//                .fillMaxWidth(0.5f)
//                .fillMaxHeight(0.7f),
//            painter = painterResource(id = onBoardingPage.image),
//            contentDescription = "Pager Image"
//        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onboardingPage.title,
//            fontSize = MaterialTheme.typography.h4.fontSize,
//            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = onboardingPage.description,
//            fontSize = MaterialTheme.typography.subtitle1.fontSize,
//            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}