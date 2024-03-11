package com.licoding.uber

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.licoding.uber.core.presentation.MainUIEvent
import com.licoding.uber.core.presentation.MainViewModel
import com.licoding.uber.search.presentation.*
import com.licoding.uber.ui.theme.UberTheme

class SearchActivity: ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

         val route = intent.getStringExtra("route")
        val permissions =  arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        val viewModel by viewModels<MainViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return MainViewModel(application) as T
                    }
                }
            }
        )
        if(!checkLocationPermission(permissions)) {
            requestPermissions(permissions)
        } else {
            requestLocationUpdates(permissions, viewModel::onEvent)
        }

        setContent {
            val state by viewModel.state.collectAsState()
            UberTheme {
                val navController = rememberNavController()
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = route!!
                    ) {
                        composable("search") {
                            Search(
                                onEvent = viewModel::onEvent,
                                places = viewModel.places,
                                navController = navController,
                                state = state
                            )
                        }
                        composable("saved") {
                            SavedPlaces(navController)
                        }
                        composable("mapview") {
                            CarOptions(
                                directions = state.directions,
                            )
                        }
                        composable("package") {
                           Package()
                        }
                    }
                }
            }
        }
    }

    private fun requestPermissions(permissions: Array<String>) {
        ActivityCompat.requestPermissions(
            this,
           permissions,
            0
        )
    }

    private fun checkLocationPermission(permissions: Array<String>): Boolean {
        return permissions.all {perm ->
            ContextCompat.checkSelfPermission(
                this,
                perm
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestLocationUpdates(permissions: Array<String>, onEvent:(MainUIEvent) -> Unit) {
        if (checkLocationPermission(permissions)) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val latlng = "${location.latitude}%2C${location.longitude}"
                        onEvent(MainUIEvent.UpdateLocation(latlng))
                        Toast.makeText(
                            this,
                            "Location: ${location.latitude}, ${location.longitude}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Unable to retrieve location",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}