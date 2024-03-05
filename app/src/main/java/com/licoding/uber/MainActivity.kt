package com.licoding.uber

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.licoding.uber.core.domain.models.BottomNavigationItem
import com.licoding.uber.activity.presentation.Activity
import com.licoding.uber.auth.data.remote.signup.google.GoogleAuthUiClient
import com.licoding.uber.home.presentation.Home
import com.licoding.uber.profile.presentation.Profile
import com.licoding.uber.services.presentation.Services
import com.licoding.uber.ui.theme.UberTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentUser = googleAuthUiClient.getSignedInUser()
        setContent {
            var selectedRoute by remember {
                mutableStateOf("home")
            }
            val items = listOf(
                BottomNavigationItem(
                    label = "Home",
                    icon = Icons.Default.Home,
                    route = "home"
                ),
                BottomNavigationItem(
                    label = "Services",
                    icon = Icons.Default.Apps,
                    route = "services"
                ),
                BottomNavigationItem(
                    label = "Activity",
                    icon = Icons.Default.Bookmark,
                    route = "activity"
                ),
                BottomNavigationItem(
                    label = "Profile",
                    icon = Icons.Filled.Person,
                    route = "profile"
                )
            )

            val scope = rememberCoroutineScope()
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
                                Profile(
                                    navController = navController,
                                    user = currentUser!!,
                                    onClick = {
                                        scope.launch {
                                            googleAuthUiClient.signOut()
                                        }
                                    }
                                )
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

