package com.licoding.uber.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.licoding.uber.R


@Composable
fun Welcome(navController: NavController) {
    val height = LocalConfiguration.current.screenHeightDp.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF162adb))
            .padding(10.dp, 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .height(height/1.7f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Uber",
                fontSize = 30.sp
            )
            Image(
                painter = painterResource(R.drawable.safep),
                modifier = Modifier
                    .size(200.dp),
                contentDescription = "null"
            )
            Text(
                text = "Move with Safety",
                fontSize = 30.sp
            )

        }
        TextButton(
            onClick = {
                      navController.navigate("login")
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, shape = RoundedCornerShape(10.dp)),

        ) {
            Row {
                Text(
                    text = "Get Started",
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Arrow"
                )
            }
        }
    }
}