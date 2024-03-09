package com.licoding.uber.core.presentation

import com.licoding.uber.search.models.Place

data class MainUIState(
    val places: List<Place>,
    val destination: String? = null
)
