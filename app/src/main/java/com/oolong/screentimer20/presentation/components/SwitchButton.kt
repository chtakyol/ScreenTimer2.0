package com.oolong.screentimer20

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SwitchButtonWithText(
    text: String,
    initialStatus: Boolean,
    onStatusChange: (Boolean) -> Unit
){
    var status by remember { mutableStateOf(initialStatus) }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = text
        )
        Switch(
            checked = status,
            onCheckedChange = {
                status = !status
                onStatusChange(it)
        })
    }
}

@Composable
@Preview(showBackground = true)
fun SwitchButtonWithTextPreview(){
    SwitchButtonWithText(text = "Text", initialStatus = true){

    }
}