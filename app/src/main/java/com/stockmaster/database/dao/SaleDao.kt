package com.stockmaster.database.dao

import android.content.ContentValues
import com.stockmaster.database.DatabaseHelper
import com.stockmaster.models.Sale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaleDao(private val databaseHelper: DatabaseHelper) {

    suspend fun insertSale(sale: Sale): Long = withContext(Dispatchers.IO) {
        databaseHelper.writableDatabase.insert(
            "sales",
            null,
            ContentValues().apply {
                put("product_id", sale.productId)
                put("quantity_sold", sale.quantitySold)
                put("sale_price", sale.salePrice)
                put("sale_date", sale.saleDate)
                put("notes", sale.notes)
            }
        )
    }

    suspend fun getSalesForProduct(productId: Int): List<Sale> = withContext(Dispatchers.IO) {
        databaseHelper.readableDatabase.rawQuery(
            "SELECT * FROM sales WHERE product_id = ? ORDER BY sale_date DESC",
            arrayOf(productId.toString())
        ).use { cursor ->
            val sales = mutableListOf<Sale>()
            while (cursor.moveToNext()) {
                sales.add(
                    Sale(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        productId = cursor.getInt(cursor.getColumnIndexOrThrow("product_id")),
                        quantitySold = cursor.getInt(cursor.getColumnIndexOrThrow("quantity_sold")),
                        salePrice = cursor.getDouble(cursor.getColumnIndexOrThrow("sale_price")),
                        saleDate = cursor.getLong(cursor.getColumnIndexOrThrow("sale_date")),
                        notes = cursor.getString(cursor.getColumnIndexOrThrow("notes")) ?: ""
                    )
                )
            }
            sales
        }
    }

    suspend fun getAllSales(): List<Sale> = withContext(Dispatchers.IO) {
        databaseHelper.readableDatabase.rawQuery(
            "SELECT * FROM sales ORDER BY sale_date DESC",
            emptyArray()
        ).use { cursor ->
            val sales = mutableListOf<Sale>()
            while (cursor.moveToNext()) {
                sales.add(
                    Sale(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        productId = cursor.getInt(cursor.getColumnIndexOrThrow("product_id")),
                        quantitySold = cursor.getInt(cursor.getColumnIndexOrThrow("quantity_sold")),
                        salePrice = cursor.getDouble(cursor.getColumnIndexOrThrow("sale_price")),
                        saleDate = cursor.getLong(cursor.getColumnIndexOrThrow("sale_date")),
                        notes = cursor.getString(cursor.getColumnIndexOrThrow("notes")) ?: ""
                    )
                )
            }
            sales
        }
    }

    suspend fun deleteSale(id: Int): Int = withContext(Dispatchers.IO) {
        databaseHelper.writableDatabase.delete("sales", "id = ?", arrayOf(id.toString()))
    }
}

