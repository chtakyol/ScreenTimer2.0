package com.oolong.screentimer20.presentation.countdown_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircularDurationBar(
    percentage: Float = 100f
)
{
    val backgroundIndicatorColor = Color(0xFF383838)
    val foregroundIndicatorColor = Color(0xFFBB86FC)
    // https://stackoverflow.com/questions/929103/convert-a-number-range-to-another-range-maintaining-ratio
    val oldRange = (100f - 0f)
    val newRange = (220f - 0f)
    val newValue = (((percentage - 0f) * newRange) / oldRange)
    Column(
        modifier = Modifier
            .size(300.dp)
            .drawBehind {
                val componentSize = size / 1.25f
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = 90f,
                )
                foregroundIndicator(
                    sweepAngle = newValue,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = 45f,
                )
            }
    ) {}
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color = Color.Gray,
    indicatorStrokeWidth: Float,
){
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 160f,
        sweepAngle = 220f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color = Color.Green,
    indicatorStrokeWidth: Float,
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 160f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

@Composable
@Preview
fun CircularDurationBarPreview()
{
    CircularDurationBar()
}