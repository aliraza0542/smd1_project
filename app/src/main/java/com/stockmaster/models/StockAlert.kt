package com.stockmaster.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "stock_alerts")
data class StockAlert(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val productName: String,
    val currentStock: Int,
    val threshold: Int,
    val alertDate: Long = System.currentTimeMillis(),
    var isRead: Boolean = false
) : Serializable
