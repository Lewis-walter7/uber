package com.licoding.uber.auth.domain

import android.content.Context
import android.telephony.TelephonyManager
import com.licoding.uber.core.data.countryPhoneCodes
import java.util.*

fun getCountryCode(context: Context): String{
    return try {
        val telephoneManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        val contryIso = telephoneManager.simCountryIso
        if (contryIso.isNotEmpty() && contryIso != null) {
            val countryCode = getCode(contryIso)
            countryCode!!
        } else {
            ""
        }
    } catch (e: Exception) {
        println(e.stackTrace) as String
    }

}

fun getCode(countryIso: String?): String {
    return countryPhoneCodes[countryIso?.uppercase(Locale.ROOT)]?.get("code").toString()
}

fun getPhoneNumberLen(countryIso: String?): String {
    return countryPhoneCodes[countryIso?.uppercase(Locale.ROOT)]?.get("length").toString()
}