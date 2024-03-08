package com.licoding.uber.search.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.licoding.uber.core.presentation.MainUIEvent


@Composable
fun Search(
    onEvent: (MainUIEvent) -> Unit,
    places: List<String>
) {

    var userLocation by remember {
        mutableStateOf("")
    }
    var searchQuery by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(5.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.onBackground, shape = CircleShape)
                        .size(5.dp)
                )
                VerticalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .height(35.dp)
                )
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.onBackground, shape = RectangleShape)
                        .size(5.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .width(10.dp)
            )
            Column {
                BasicTextField(
                    onValueChange = {
                          userLocation = it
                    },
                    value = userLocation,
                    decorationBox = { innerTextField ->
                        if(userLocation.isEmpty()) {
                            Text(
                                text = "Where to?"
                            )
                        }
                        innerTextField()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(6.dp))
                        .padding(10.dp),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                BasicTextField(
                    onValueChange = {
                         searchQuery = it
                        onEvent(MainUIEvent.OnSearchQueyChange(searchQuery))
                    },
                    value = searchQuery,
                    decorationBox = { innerTextField ->
                        if(searchQuery.isEmpty()) {
                            Text(
                                text = "Where to?"
                            )
                        }
                        innerTextField()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(6.dp))
                        .padding(10.dp),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }

        if (places.isEmpty()) {
            Text(
                text = "No places Found"
            )
        } else {
            LazyColumn {
                items(places) { place ->
                    Text(
                        text = place
                    )
                }
            }
        }
    }
}
