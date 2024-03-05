package com.licoding.uber.home.domain.models

import androidx.compose.ui.graphics.painter.Painter

data class SaveModel(
    val image: Painter,
    val label:String,
    val description: String
)
