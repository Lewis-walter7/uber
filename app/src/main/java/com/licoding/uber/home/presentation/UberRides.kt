package com.licoding.uber.home.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.licoding.uber.core.presentation.MainUIEvent
import com.licoding.uber.home.presentation.components.CustomInputField
import com.licoding.uber.home.presentation.components.Save
import com.licoding.uber.home.presentation.components.Suggestions

@Composable
fun UberRides(navController: NavController, onEvent: (MainUIEvent) -> Unit, navigate: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(15.dp, 25.dp)
    ) {
        item {
            CustomInputField(navController, navigate)
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Suggestions(title = "Suggestions", true, navigate)
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Save()
        }
    }
}