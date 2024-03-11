package com.licoding.uber.search.models

import androidx.compose.ui.graphics.painter.Painter

data class Ride(
    val painter: Painter,
    val name: String,
    val price: String,
    val timeAway: String,
    val description: String,
    val numberOfPeople: Int
)
