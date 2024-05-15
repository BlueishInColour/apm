package com.blueishincolour.apm.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ApmTonalFilledButton(
    text:String,
    onClick:()->Unit
) {
    FilledTonalButton(
        shape = RoundedCornerShape(1.dp),
        onClick =onClick,
        modifier = Modifier
            .fillMaxWidth()) {
        Text(text = text)
    }
}