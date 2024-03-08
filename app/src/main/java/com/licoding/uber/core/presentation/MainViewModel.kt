package com.licoding.uber.core.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.licoding.uber.core.domain.services.PlacesService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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
        }
    }
}