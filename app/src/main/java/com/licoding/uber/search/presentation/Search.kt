package com.licoding.uber.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.licoding.uber.core.presentation.MainUIEvent
import com.licoding.uber.core.presentation.MainUIState
import com.licoding.uber.search.models.NearbyPlace
import com.licoding.uber.search.models.Place


@Composable
fun Search(
    onEvent: (MainUIEvent) -> Unit,
    places: MutableSet<Place>,
    navController: NavController,
    state: MainUIState
) {

    var userLocation by remember {
        mutableStateOf("")
    }
    var searchQuery by remember {
        mutableStateOf("")
    }
    LaunchedEffect(searchQuery) {
        onEvent(MainUIEvent.OnSearchQueyChange(searchQuery))
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
                        //onEvent(MainUIEvent.OnSearchQueyChange(searchQuery))
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
        Spacer(modifier = Modifier.height(10.dp))
        if (places.isEmpty() && searchQuery.isNotEmpty()) {
            Text(
                text = "No places Found"
            )
        } else if(places.isEmpty() && state.nearbyPlaces.isNotEmpty()) {
            LazyColumn {
                items(state.nearbyPlaces) { place ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                            .clickable {
                                navController.navigate("mapview")
                                onEvent(MainUIEvent.SetDestination(place.id))
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = place.name,
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = place.vicinity,
                            )
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        } else {
            LazyColumn {
                items(places.toList()) { place ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                            .clickable {
                                navController.navigate("mapview")
                                onEvent(MainUIEvent.SetDestination(place.id))
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = place.place,
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = place.location,
                            )
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        }
    }
}
