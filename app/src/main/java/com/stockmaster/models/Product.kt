package com.stockmaster.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: String,
    val description: String = "",
    val purchasePrice: Double,
    val sellingPrice: Double,
    var stockQuantity: Int,
    val lowStockThreshold: Int = 10,
    val barcode: String = "",
    val dateAdded: Long = System.currentTimeMillis()
) : Serializable
