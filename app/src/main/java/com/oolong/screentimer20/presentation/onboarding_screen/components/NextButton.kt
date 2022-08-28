package com.oolong.screentimer20.presentation.onboarding_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun NextButton(
    pagerState: PagerState = rememberPagerState(),
    onClick: () -> Unit = {}
) {
    val buttonBackgroundColor = Color(0xFFFFFFFF)
    val buttonContentColor = Color(0xFF212121)
    val buttonColor = ButtonDefaults.buttonColors(
        backgroundColor = buttonBackgroundColor,
    )
    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth(),
        visible = pagerState.currentPage == 1
    ) {
        Button(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, bottom = 64.dp),
            colors = buttonColor,
            onClick = {
                onClick()
            }
        ) {
            Text(
                text = "Let's configure device admin settings",
                color = buttonContentColor
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
@Preview
fun PreviewNextButton() {
    NextButton()
}