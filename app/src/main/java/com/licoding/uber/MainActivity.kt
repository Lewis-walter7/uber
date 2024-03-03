package com.licoding.uber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.licoding.uber.core.domain.models.BottomNavigationItem
import com.licoding.uber.main.presentation.Activity
import com.licoding.uber.main.presentation.Home
import com.licoding.uber.main.presentation.Profile
import com.licoding.uber.main.presentation.Services
import com.licoding.uber.ui.theme.UberTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selectedRoute by remember {
                mutableStateOf("")
            }
            val items = listOf(
                BottomNavigationItem(
                    label = "Home",
                    icon = Icons.Default.Home,
                    route = "home"
                ),
                BottomNavigationItem(
                    label = "Services",
                    icon = Icons.Default.Home,
                    route = "services"
                ),
                BottomNavigationItem(
                    label = "Activity",
                    icon = Icons.Default.Home,
                    route = "activity"
                ),
                BottomNavigationItem(
                    label = "Profile",
                    icon = Icons.Filled.Person,
                    route = "profile"
                )
            )
            UberTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomAppBar {
                                items.map { item ->
                                    NavigationBarItem(
                                        onClick = {
                                            selectedRoute = item.route
                                            navController.navigate(item.route)
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = item.icon,
                                                contentDescription = null
                                            )
                                        },
                                        selected = selectedRoute == item.route,
                                        label = {
                                            Text(
                                                text = item.label
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "home"
                        ) {
                            composable("home") {
                                Home(navController)
                            }
                            composable("profile") {
                                Profile(navController)
                            }
                            composable("activity") {
                                Activity(navController)
                            }
                            composable("services") {
                                Services(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

