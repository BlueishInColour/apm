package com.blueishincolour.apm.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ApmTextField(
    hintText:String,
    value:String,
    onChange:(String)->Unit
) {
    TextField(
        value = value,
        placeholder = { Text(text = hintText)},
        onValueChange ={
            onChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
        )
}