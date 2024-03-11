package com.licoding.uber.services.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.licoding.uber.R
import com.licoding.uber.services.domain.Anything

@Composable
fun GetAnything() {
    val items = listOf(
        Anything(
            title = "Food",
            image = painterResource(R.drawable.food),
            route = "food"
        ),
        Anything(
            title = "Convenience",
            image = painterResource(R.drawable.convinience),
            route = "convinience"
        ),
        Anything(
            title = "Food",
            image = painterResource(R.drawable.pharmacy),
            route = "pharmacy"
        )
    )
    val screenWith = LocalConfiguration.current.screenWidthDp.dp


    Column(
        modifier = Modifier
            .padding(15.dp)
    ) {
        Text(
            text = "Get anything delivered",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 23.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items.forEach { suggestion ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(6.dp))
                ) {
                    Image(
                        painter = suggestion.image,
                        contentDescription = null,
                        modifier = Modifier
                            .width(55.dp)
                    )
                    Text(
                        text = suggestion.title,
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.background
                    )
                }
            }
        }
    }
}