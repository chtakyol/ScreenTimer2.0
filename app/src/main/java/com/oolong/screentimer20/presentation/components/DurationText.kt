package com.oolong.screentimer20

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
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
                fontSize = 24.sp,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = stringResource(id = R.string.hour_short_text),
                modifier = Modifier.offset(y = (-2).dp),
                color = MaterialTheme.colors.onSurface
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = minuteVal.toString(),
                fontSize = 24.sp,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = stringResource(id = R.string.minute_short_text),
                modifier = Modifier.offset(y = (-2).dp),
                color = MaterialTheme.colors.onSurface
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = secondVal.toString(),
                fontSize = 24.sp,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = stringResource(id = R.string.second_short_text),
                modifier = Modifier.offset(y = (-2).dp),
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DurationTextPreview(){
    DurationText()
}