package com.stockmaster.utils

import com.stockmaster.R

object ProfitCalculator {

    fun calculateProfit(purchase: Double, selling: Double, qty: Int): Double {
        return (selling - purchase) * qty
    }

    fun calculateMarginPercent(purchase: Double, selling: Double): Double {
        return if (selling > 0) ((selling - purchase) / selling) * 100.0 else 0.0
    }

    fun getMarginLabel(margin: Double): String {
        return when {
            margin > 30 -> "Excellent"
            margin > 20 -> "Good"
            margin > 10 -> "Fair"
            else -> "Poor"
        }
    }

    fun getMarginColorRes(margin: Double): Int {
        return when {
            margin > 20 -> R.color.secondary
            margin > 10 -> R.color.on_tertiary_container
            else -> R.color.error
        }
    }

    fun calculateMarginFromPrices(purchase: Double, selling: Double): Double {
        return if (selling > 0) ((selling - purchase) / selling) * 100.0 else 0.0
    }
}
