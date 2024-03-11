package com.licoding.uber.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SheetContent(){
    var bottomSheetHeight by remember { mutableStateOf(0.dp) }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }

    val items = listOf(
        "Recommended", "Price", "Pickup time"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { layoutCoordinates ->
                bottomSheetHeight = layoutCoordinates.size.height.dp
            },
    ){
        Text(
            text = "Choose your Ride",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        if (bottomSheetHeight >= (screenHeight - 120.dp)) {
           Column {
               LazyRow {
                   item {
                       items.forEachIndexed { index, item ->
                           Text(
                               text = item,
                               modifier = Modifier
                                   .background(MaterialTheme.colorScheme.onSecondaryContainer, RoundedCornerShape(50))
                                   .clickable {
                                       selectedItemIndex = index
                                   }
                           )
                       }
                   }
               }

               Text(
                   text = "Rides we think you will like"
               )
           }
        }
    }
}