package com.licoding.uber.services.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.licoding.uber.home.presentation.components.Suggestions

@Composable
fun Services(navController: NavController, navigate: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Services",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.padding(15.dp)
        ) {
            Suggestions(
                title = "Go anywhere",
                false,
                navigate = {
                    navigate()
                }
            )
        }
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.inverseSurface,
            modifier = Modifier
                .fillMaxWidth()
        )

        GetAnything()
    }
}