package com.licoding.uber.home.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.licoding.uber.home.presentation.components.CustomInputField
import com.licoding.uber.home.presentation.components.Save
import com.licoding.uber.home.presentation.components.Suggestions

@Composable
fun UberRides() {
    LazyColumn(
        modifier = Modifier
            .padding(15.dp, 25.dp)
    ) {
        item {
            CustomInputField()
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Suggestions()
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Save()
        }
    }
}