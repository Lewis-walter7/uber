package com.licoding.uber.core.domain.services

import android.content.Context
import android.widget.Toast
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
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
    var places = mutableListOf<String>()
    fun findPlaceSuggestions(searchQuery: String){
        val token = AutocompleteSessionToken.newInstance()
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(searchQuery)
            .setSessionToken(token)
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                autocompletePredictions = response.autocompletePredictions

                autocompletePredictions.forEach { prediction ->
                    places.add(prediction.getFullText(null).toString())
                }
            }.addOnFailureListener { exception ->
                scope.launch {
                    Toast.makeText(context, "No places Found", Toast.LENGTH_SHORT).show()
                }
            }
    }
}