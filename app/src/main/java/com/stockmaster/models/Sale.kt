package com.stockmaster.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "sales")
data class Sale(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val productName: String,
    val quantitySold: Int,
    val purchasePrice: Double,
    val sellingPrice: Double,
    val totalRevenue: Double,
    val totalProfit: Double,
    val profitMarginPercent: Double,
    val saleDate: Long = System.currentTimeMillis()
) : Serializable
