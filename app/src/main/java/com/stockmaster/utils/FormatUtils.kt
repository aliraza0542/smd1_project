package com.stockmaster.utils

import java.text.NumberFormat
import java.util.Locale

object FormatUtils {
    private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US)

    fun currency(value: Double): String = currencyFormatter.format(value)
}

