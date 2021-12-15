package com.oolong.screentimer20

import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.*
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay


@ExperimentalComposeUiApi
@Composable
fun CircularSlider(
    canvasSize: Dp = 300.dp,
    indicatorValue: Float = 0f,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorColor: Color = MaterialTheme.colors.onSecondary.copy(alpha = ContentAlpha.disabled),
    backgroundIndicatorStrokeWidth: Float = 100f,
    foregroundIndicatorColor: Color = MaterialTheme.colors.primary,
    foregroundIndicatorStrokeWidth: Float = 80f,
    handleColor: Color = MaterialTheme.colors.primaryVariant,
    handleStrokeWidth: Float = 125f,
    isTimerRunning: Boolean = false,
    onValueChange: (Float) -> Unit
){
    var totalAngle by remember { mutableStateOf(indicatorValue) }

    var fixedAngle by remember { mutableStateOf(0f) }

    var shouldUpScale by remember { mutableStateOf(false) }

    var shouldDownScale by remember { mutableStateOf(false) }

    var angleScalar by remember { mutableStateOf(0) }

    val percentage = (totalAngle / maxIndicatorValue) * 100

    var sweepAngle by remember { mutableStateOf(0f)}

//    Animated foreground which not that necessary
//    val sweepAngle by animateFloatAsState(
//        targetValue = (1 * percentage).toFloat(),
//        animationSpec = tween(100)
//    )

//    LaunchedEffect(key1 = totalAngle){
//        if (isTimerRunning) {
//            delay(100)
//            totalAngle -= 1
//        }
//    }

    var touchX by remember { mutableStateOf(0f) }
    var touchY by remember { mutableStateOf(0f) }
    var centerX by remember { mutableStateOf(0f) }
    var centerY by remember { mutableStateOf(0f) }

    sweepAngle = if (!isTimerRunning) {(1 * percentage).toFloat()} else indicatorValue
    totalAngle = sweepAngle

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / 1.25f
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth
                )
            }
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }
            .pointerInteropFilter {
                touchX = it.x
                touchY = it.y
                when (it.actionMasked) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                        if (!isTimerRunning){
                            val angle =
                                -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()
                            fixedAngle = if (angle in -180f..0f) {
                                360f + angle
                            } else {
                                angle
                            }

                            if (fixedAngle in 270f..360f) {
                                shouldUpScale = true
                            }

                            if (fixedAngle in 0f..90f &&
                                shouldUpScale &&
                                totalAngle < fixedAngle + angleScalar * 360f
                            ) {
                                angleScalar++
                                shouldUpScale = false
                            }

                            if (fixedAngle in 0f..90f) {
                                shouldDownScale = true
                            }

                            if (fixedAngle in 90f..270f){
                                shouldDownScale = false
                                shouldUpScale = false
                            }

                            if (fixedAngle in 270f..360f &&
                                shouldDownScale &&
                                totalAngle > fixedAngle + angleScalar * 360 &&
                                angleScalar > 0
                            ) {
                                angleScalar--
                                shouldDownScale = false
                            }

                            totalAngle = fixedAngle + angleScalar * 360
                            sweepAngle = if (!isTimerRunning) {(1 * percentage).toFloat()} else indicatorValue
//                        Log.d(
//                            "SliderInfo",
//                            "fixedAngle $fixedAngle totalAngle ${totalAngle}, angleScalar $angleScalar"
//                        )
                            onValueChange(sweepAngle)
                        }
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        shouldDownScale = false
                        shouldUpScale = false
                        true
                    }
                    else -> false
                }
            }
    ) {
    }
}

fun DrawScope.handle(
    size: Size,
    value: Float,
    pointMode: PointMode = PointMode.Points,
    handleColor: Color,
    handleStrokeWidth: Float,
    cap: StrokeCap = StrokeCap.Round
){
    val center = Offset(size.width / 2f, size.height / 2f)
    val beta = (245f* 1 + 145f) * (PI / 180f).toFloat()
    val r = size.width / 2f
    val a = cos(beta) * r
    val b = sin(beta) * r
    drawPoints(
        listOf(Offset(
            center.x + a + handleStrokeWidth / 2f,
            center.y + b + handleStrokeWidth / 2f)
        ),
        pointMode = pointMode,
        color = handleColor,
        strokeWidth = handleStrokeWidth,
        cap = cap
    )
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
){
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 270f,
        sweepAngle = 360f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2,
            y = (size.height - componentSize.height) / 2
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
){
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 270f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2,
            y = (size.height - componentSize.height) / 2
        )
    )
}

@ExperimentalComposeUiApi
@Composable
@Preview(showBackground = true)
fun CircularSliderPreview(){
    CircularSlider(){

    }
}