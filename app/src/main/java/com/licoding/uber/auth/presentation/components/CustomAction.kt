package com.licoding.uber.auth.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun CustomAction() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            tint = Color.Black,
            modifier = Modifier
                .size(20.dp)
        )
        Spacer(
            modifier = Modifier
                .width(10.dp)
        )
        Text(
            text = "Find my account",
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
    }
}