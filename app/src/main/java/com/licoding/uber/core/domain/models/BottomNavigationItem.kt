package com.licoding.uber.core.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)
