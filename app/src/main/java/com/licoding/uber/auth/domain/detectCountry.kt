package com.licoding.uber.auth.domain

import android.content.Context
import android.telephony.TelephonyManager

fun detectCountry(context: Context): String {

    return try {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val countryIso = telephonyManager.networkCountryIso

        if (countryIso != null && countryIso.isNotEmpty()) {
            countryIso
        } else {
            ""
        }
    } catch (e: Exception) {
        println(e.stackTrace) as String
    }
}