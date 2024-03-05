package com.licoding.uber.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.licoding.uber.R
import com.licoding.uber.home.domain.models.Suggestion

@Composable
fun Suggestions() {
    val breakPointIndex = 5
    val pickup = "Store Pickup"
    val anotherNetwork = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 15.sp)) {
            append(pickup.substring(0, breakPointIndex))
        }
        append("\n")
        withStyle(style = SpanStyle(fontSize = 15.sp)) {
            append(pickup.substring(breakPointIndex))
        }
    }.toString()

    val suggestions = listOf(
        Suggestion(
            image = painterResource(R.drawable.ubercar),
            label = "Uber"
        ),
        Suggestion(
            image = painterResource(R.drawable.box),
            label = "Package"
        ),
        Suggestion(
            image = painterResource(R.drawable.reserve),
            label = "Reserve"
        ),
        Suggestion(
            image = painterResource(R.drawable.pickup),
            label = anotherNetwork
        )
    )



    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Suggestions",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            TextButton(
                onClick = {

                }
            ) {
                Text(
                    text = "See All",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            suggestions.forEach { suggestion ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(6.dp))
                    ) {
                        Image(
                            painter = suggestion.image,
                            contentDescription = null,
                            modifier = Modifier
                                .width(45.dp)
                        )
                    }
                    Text(
                        text = suggestion.label,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}