package com.licoding.uber.search.presentation

import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.*
import com.google.maps.android.ktx.model.markerOptions
import com.licoding.uber.R

@Composable
fun MapView(directions: List<LatLng>, padding: Dp){
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties by remember {
        mutableStateOf(MapProperties())
    }
    if (directions.isEmpty()) {
        return
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(directions.first(), 13f)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.weight(1f)
                .fillMaxWidth()
        ) {
            GoogleMap(
                modifier = Modifier.matchParentSize(),
                properties = properties,
                uiSettings = uiSettings,
                cameraPositionState = cameraPositionState,
                contentPadding = PaddingValues(padding)
            ) {
                if (directions.isNotEmpty()) {
                    Polyline(
                        points = directions,
                        color = Color.Black,
                        width = 5f
                    )
                }
                Marker(
                    title = "Uber --fast",
                    state = MarkerState(position = directions.first()),
                    icon = BitmapDescriptorFactory.fromResource(R.drawable.ubercarsm),
                    zIndex = 10f,
                )
            }
            Switch(
                checked = uiSettings.zoomControlsEnabled,
                onCheckedChange = {
                    uiSettings = uiSettings.copy(zoomControlsEnabled = it)
                }
            )
        }
    }
}


