package com.example.core.common

import android.icu.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

fun Double.toRupees(): String {
    val indiaLocale = Locale.Builder().setLanguage("en").setRegion("IN").build()
    val format = NumberFormat.getCurrencyInstance(indiaLocale)

    return if (this < 0) {
        "-${format.format(abs(this))}"
    } else {
        format.format(this)
    }
}