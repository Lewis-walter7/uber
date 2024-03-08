package com.licoding.uber.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.licoding.uber.R
import com.licoding.uber.core.presentation.MainUIEvent
import com.licoding.uber.home.domain.models.Category
import com.licoding.uber.home.presentation.components.Category

@Composable
fun Home(navController: NavController, onEvent: (MainUIEvent) -> Unit, navigate: () -> Unit) {
    val categories = listOf(
        Category(
            label = "Rides",
            image = painterResource(R.drawable.ubercar)
        ),
        Category(
            label = "Eats",
            image = painterResource(R.drawable.ubereats)
        )
    )
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            categories.forEachIndexed { index, category ->
                Category(
                    category = category,
                    onClick = {
                        selectedIndex = index
                        println(selectedIndex)
                    },
                    selectedIndex = selectedIndex,
                    index = index
                )
            }
        }
        if (selectedIndex == 0) {
            UberRides(navController, onEvent, navigate)
        } else {
            UberEats()
        }
    }

}