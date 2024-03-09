package com.licoding.uber.core.domain.services

import android.content.Context
import android.widget.Toast
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.licoding.uber.search.models.Place
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlacesService(private val context: Context) {
    init {
        Places.initialize(context, ApiKeyProvider.APIKEY)
    }
    private val scope = CoroutineScope(Dispatchers.Main)
    private val placesClient = Places.createClient(context)

    private var autocompletePredictions =  mutableListOf<AutocompletePrediction>()
    var places = mutableSetOf<Place>()
    fun findPlaceSuggestions(searchQuery: String){
        places.clear()
        val token = AutocompleteSessionToken.newInstance()
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(searchQuery)
            .setSessionToken(token)
            .build()
        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                autocompletePredictions = response.autocompletePredictions

                autocompletePredictions.forEach { prediction ->
                    val place = Place(
                        id = prediction.placeId,
                        place = prediction.getPrimaryText(null).toString(),
                        location = prediction.getSecondaryText(null).toString()
                    )
                    places.add(place)
                }
            }.addOnFailureListener {
                scope.launch {
                    Toast.makeText(context, "No places Found", Toast.LENGTH_SHORT).show()
                }
            }
    }
}