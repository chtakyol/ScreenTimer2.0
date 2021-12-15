package com.oolong.screentimer20

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DurationText(
    hourVal: Int = 0,
    minuteVal: Int = 0,
    secondVal: Int = 0
){
    Row(
        modifier = Modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
        ){
            Text(
                text = hourVal.toString(),
                fontSize = 24.sp
            )
            Text(
                text = "hr",
                modifier = Modifier.offset(y = (-2).dp)
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = minuteVal.toString(),
                fontSize = 24.sp
            )
            Text(
                text = "min",
                modifier = Modifier.offset(y = (-2).dp)
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = secondVal.toString(),
                fontSize = 24.sp
            )
            Text(
                text = "sec",
                modifier = Modifier.offset(y = (-2).dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DurationTextPreview(){
    DurationText()
}