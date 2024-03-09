package com.licoding.uber.core.domain.services

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import com.licoding.uber.core.data.remote.client
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.json.JSONObject

object DirectionsService {

    suspend fun getDirections(origin: String, destination: String): List<LatLng> {
        val url = "https://maps.googleapis.com/maps/api/directions/json?origin=$origin&destination=$destination&key=${ApiKeyProvider.APIKEY}"
        val response = client.get(url)
        val json = response.body<String>()

        return parseDirections(json)
    }
    private fun parseDirections(json: String?): List<LatLng> {
        val directions = mutableListOf<LatLng>()
        val jsonObject = json?.let { JSONObject(it) }
        val routesArray = jsonObject?.getJSONArray("routes")
        val routeObject = routesArray?.getJSONObject(0)
        val legsArray = routeObject?.getJSONArray("legs")
        val stepsArray = legsArray?.getJSONObject(0)?.getJSONArray("steps")

        if (stepsArray != null) {
            for (i in 0 until stepsArray.length()) {
                val stepObject = stepsArray.getJSONObject(i)
                val polylineObject = stepObject.getJSONObject("polyline")
                val points = polylineObject.getString("points")
                val decodedPoints = PolyUtil.decode(points)
                directions.addAll(decodedPoints)
            }
        }

        return directions
    }
}