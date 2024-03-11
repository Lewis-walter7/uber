package com.licoding.uber.core.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licoding.uber.core.domain.services.DirectionsService
import com.licoding.uber.core.domain.services.NearbyPlacesService
import com.licoding.uber.core.domain.services.PlacesService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    context: Context,
): ViewModel() {
    private val placesService = PlacesService(context)

    private val _state = MutableStateFlow(MainUIState(placesService.places))
    val state = _state.asStateFlow()
    val places = placesService.places
    fun onEvent(event: MainUIEvent) {
        when (event) {
            is MainUIEvent.OnSearchQueyChange -> {
                placesService.findPlaceSuggestions(searchQuery = event.searchQuery)
            }

            is MainUIEvent.SetDestination -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            destination = event.destination
                        )
                    }

                    val directions =
                        DirectionsService.getDirections(
                            origin = "${state.value.location}",
                            destination = "place_id:${state.value.destination}"
                        )
                    delay(1000)
                    _state.update {
                        it.copy(
                            directions = directions,
                            distance = DirectionsService.result
                        )
                    }
                }
            }

            is MainUIEvent.UpdateLocation -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            location = event.location,
                            nearbyPlaces = NearbyPlacesService.getNearbyPlaces(event.location).take(5)
                        )
                    }
                }
            }
        }
    }
}