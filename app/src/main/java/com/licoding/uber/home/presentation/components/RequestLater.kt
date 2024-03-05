package com.licoding.uber.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RequestLater(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = {
                navController.navigate("home")
            }
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "close"
            )
        }
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Text(
            text = "When do you want to be picked up?",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier.height(30.dp)
        )
        Text(
            text = "Mon, Mar 4",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(
            modifier = Modifier.height(30.dp)
        )
        Text(
            text = "9:15 AM",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(
            modifier = Modifier.height(30.dp)
        )
        Column {
            Row {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "close"
                    )
                }
                Column {
                    Text(
                        text = "Choose your exact pickup time to 90 days in advance",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Thin
                    )
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Row {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.HourglassTop,
                        contentDescription = "close"
                    )
                }
                Column {
                    Text(
                        text = "Extra wait time included to meet your ride",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Thin
                    )
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Row {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.CreditCard,
                        contentDescription = "close"
                    )
                }
                Text(
                    text = "Cancel at no charge up to 60 minutes in advance",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Thin
                )
            }
        }
        Spacer(
            modifier = Modifier.height(30.dp)
        )
        Text(
            text = "See terms",
            textDecoration = TextDecoration.Underline
        )
        Spacer(
            modifier = Modifier.height(30.dp)
        )
        TextButton(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(10.dp))
        ) {
            Text(
                text = "Set pickup time",
                color = MaterialTheme.colorScheme.background,
                fontSize = 20.sp
            )
        }
    }
}