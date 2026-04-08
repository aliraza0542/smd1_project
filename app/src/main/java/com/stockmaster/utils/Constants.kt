package com.stockmaster.utils

object Constants {
    const val PREF_NAME = "stockmaster_prefs"
    const val KEY_CURRENCY_SYMBOL = "currency_symbol"
    const val DEFAULT_CURRENCY = "Rs."

    // Intent keys
    const val KEY_USER_NAME = "USER_NAME"
    const val KEY_USER_ROLE = "USER_ROLE"
    const val KEY_USER_ID = "USER_ID"
    const val KEY_PRODUCT_OBJECT = "PRODUCT_OBJECT"
    const val KEY_SALE_OBJECT = "SALE_OBJECT"
    const val KEY_MODE = "MODE"

    // Roles
    const val ROLE_ADMIN = "ADMIN"
    const val ROLE_STAFF = "STAFF"

    // Categories
    val CATEGORIES = listOf("Electronics", "Food", "Clothing", "Other")
}
