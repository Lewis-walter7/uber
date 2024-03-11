package com.licoding.uber.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CustomInputField(navController: NavController, navigate: (String) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(30.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search"
            )
            TextButton(
                onClick = {
                    navigate("search")
                }
            ) {
                Text(
                    text = "Where to?",
                    fontSize = 20.sp
                )
            }
        }
        VerticalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(30.dp))
                .padding(6.dp)
                .clickable {
                    navController.navigate("pickuptime")
                }
        ){
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = "time"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Now",
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.ExpandMore,
                contentDescription = "time"
            )
        }
    }
}