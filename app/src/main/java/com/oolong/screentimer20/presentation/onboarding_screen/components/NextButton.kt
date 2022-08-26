package com.oolong.screentimer20.presentation.onboarding_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun NextButton(
    pagerState: PagerState,
    onClick: () -> Unit = {}
) {
    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth(),
        visible = pagerState.currentPage == 2
    ) {
        Button(
            onClick = {
                onClick()
            }
        ) {
            Text(
                text = "Let's configure device admin settings"
            )
        }
    }
}

@Composable
@Preview
fun PreviewNextButton() {
//    NextButton()
}