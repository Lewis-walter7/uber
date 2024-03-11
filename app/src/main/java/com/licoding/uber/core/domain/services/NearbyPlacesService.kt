package com.licoding.uber.core.domain.services

import com.licoding.uber.core.data.remote.client
import com.licoding.uber.search.models.NearbyPlace
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.json.JSONObject

object NearbyPlacesService {

    suspend fun getNearbyPlaces(latLng: String): MutableList<NearbyPlace> {
        val url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=$latLng&radius=15000&type=restaurant&key=${ApiKeyProvider.DIRECTIONS_API_KEY}"
        val response = client.get(url)

        val json = response.bodyAsText()

        val nearbyPlaces = mutableListOf<NearbyPlace>()
        val jsonObject = JSONObject(json)
        val resultsArray = jsonObject.getJSONArray("results")

        for(i in 0 until resultsArray.length()) {
            val resultObject = resultsArray.getJSONObject(i)
            val rating = resultObject.getDouble("rating")
            if(rating >= 4.3 && rating != null) {
                val name = resultObject.getString("name")
                val placeId = resultObject.getString("place_id")
                val vicinity = resultObject.getString("vicinity")

                val nearbyPlace = NearbyPlace(
                    id = placeId,
                    vicinity = vicinity,
                    name = name
                )
                nearbyPlaces.add(nearbyPlace)

            }
        }
        return nearbyPlaces
    }
}