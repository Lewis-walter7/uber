package com.licoding.uber.search.presentation

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarOptions(directions: List<LatLng>) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetPeekHeight = 1.dp,
        sheetContent = {
            SheetContent()
        },
    ) {
       MapView(
           directions = directions,
           padding = bottomSheetState.bottomSheetState.requireOffset().dp
       )
    }
}