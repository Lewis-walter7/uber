package com.licoding.uber.core.presentation

import com.google.android.gms.maps.model.LatLng
import com.licoding.uber.search.models.NearbyPlace
import com.licoding.uber.search.models.Place

data class MainUIState(
    val places: MutableSet<Place>,
    val destination: String? = null,
    val directions: List<LatLng> = emptyList(),
    val location: String? = null,
    val nearbyPlaces: List<NearbyPlace> = emptyList(),
    val distance: Float? = 0f
)
