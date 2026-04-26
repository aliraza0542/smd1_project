package com.stockmaster.models

data class Sale(
    val id: Int = 0,
    val productId: Int,
    val quantitySold: Int,
    val salePrice: Double,
    val saleDate: Long = System.currentTimeMillis(),
    val notes: String = ""
)

