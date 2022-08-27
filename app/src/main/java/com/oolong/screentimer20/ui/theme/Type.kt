package com.oolong.screentimer20.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.oolong.screentimer20.R

val Alata = FontFamily(
    Font(R.font.alata_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Alata,
        fontSize = 20.sp,
        color = Color(0xFFFFFFFF)
    ),
    h1 = TextStyle(
        fontFamily = Alata,
        fontSize = 54.sp,
        color = Color(0xFFFFFFFF)
    ),
    h2 = TextStyle(
        fontFamily = Alata,
        fontSize = 32.sp,
        color = Color(0xFFFFFFFF)
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)