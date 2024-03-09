package com.licoding.uber.core.domain.models

data class Trip(
    val distance: Int = 0,
    val destination: String,
    val origin: String,
    val price: Int,
    val travelTime: String
)
