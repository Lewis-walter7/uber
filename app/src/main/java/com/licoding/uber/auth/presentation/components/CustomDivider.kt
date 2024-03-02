package com.licoding.uber.auth.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = "or",
            color = Color.Gray,
            modifier = Modifier
                .padding(horizontal = 2.dp)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier
                .weight(1f)
        )
    }
}