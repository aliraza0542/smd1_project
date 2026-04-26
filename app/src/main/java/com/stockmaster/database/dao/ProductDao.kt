package com.stockmaster.database.dao

import android.content.ContentValues
import android.database.Cursor
import com.stockmaster.database.DatabaseHelper
import com.stockmaster.models.Product
import com.stockmaster.models.SalesSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductDao(private val databaseHelper: DatabaseHelper) {

    suspend fun insertProduct(product: Product): Long = withContext(Dispatchers.IO) {
        databaseHelper.writableDatabase.insert("products", null, toValues(product))
    }

    suspend fun getAllProducts(): List<Product> = withContext(Dispatchers.IO) {
        queryProducts("SELECT * FROM products ORDER BY name COLLATE NOCASE ASC", emptyArray())
    }

    suspend fun getProductById(id: Int): Product? = withContext(Dispatchers.IO) {
        databaseHelper.readableDatabase.rawQuery(
            "SELECT * FROM products WHERE id = ?",
            arrayOf(id.toString())
        ).use { cursor ->
            if (cursor.moveToFirst()) cursor.toProduct() else null
        }
    }

    suspend fun updateProduct(product: Product): Int = withContext(Dispatchers.IO) {
        databaseHelper.writableDatabase.update(
            "products",
            toValues(product),
            "id = ?",
            arrayOf(product.id.toString())
        )
    }

    suspend fun deleteProduct(id: Int): Int = withContext(Dispatchers.IO) {
        databaseHelper.writableDatabase.delete("products", "id = ?", arrayOf(id.toString()))
    }

    suspend fun searchProducts(query: String): List<Product> = withContext(Dispatchers.IO) {
        val wildcard = "%$query%"
        queryProducts(
            "SELECT * FROM products WHERE name LIKE ? OR sku LIKE ? ORDER BY name COLLATE NOCASE ASC",
            arrayOf(wildcard, wildcard)
        )
    }

    suspend fun filterByStatus(status: String): List<Product> = withContext(Dispatchers.IO) {
        queryProducts(
            "SELECT * FROM products WHERE status = ? ORDER BY name COLLATE NOCASE ASC",
            arrayOf(status)
        )
    }

    suspend fun sortByStockAscending(): List<Product> = withContext(Dispatchers.IO) {
        queryProducts("SELECT * FROM products ORDER BY stock_count ASC", emptyArray())
    }

    suspend fun sortByStockDescending(): List<Product> = withContext(Dispatchers.IO) {
        queryProducts("SELECT * FROM products ORDER BY stock_count DESC", emptyArray())
    }

    suspend fun sortByPriceDescending(): List<Product> = withContext(Dispatchers.IO) {
        queryProducts("SELECT * FROM products ORDER BY price DESC", emptyArray())
    }

    suspend fun sortByPriceAscending(): List<Product> = withContext(Dispatchers.IO) {
        queryProducts("SELECT * FROM products ORDER BY price ASC", emptyArray())
    }

    suspend fun getLowStockCount(threshold: Int = 10): Int = withContext(Dispatchers.IO) {
        databaseHelper.readableDatabase.rawQuery(
            "SELECT COUNT(*) FROM products WHERE stock_count < ?",
            arrayOf(threshold.toString())
        ).use { cursor ->
            if (cursor.moveToFirst()) cursor.getInt(0) else 0
        }
    }

    suspend fun getSalesSummaryPerProduct(): List<SalesSummary> = withContext(Dispatchers.IO) {
        val sql = """
            SELECT p.name, COALESCE(SUM(s.quantity_sold), 0) as total_sold,
                   COALESCE(SUM(s.sale_price * s.quantity_sold), 0) as revenue
            FROM products p LEFT JOIN sales s ON p.id = s.product_id
            GROUP BY p.id
            ORDER BY total_sold DESC
        """.trimIndent()
        databaseHelper.readableDatabase.rawQuery(sql, emptyArray()).use { cursor ->
            val summaries = mutableListOf<SalesSummary>()
            while (cursor.moveToNext()) {
                summaries.add(
                    SalesSummary(
                        productName = cursor.getString(0),
                        totalSold = cursor.getInt(1),
                        revenue = cursor.getDouble(2)
                    )
                )
            }
            summaries
        }
    }

    private fun queryProducts(sql: String, args: Array<String>): List<Product> {
        return databaseHelper.readableDatabase.rawQuery(sql, args).use { cursor ->
            val products = mutableListOf<Product>()
            while (cursor.moveToNext()) {
                products.add(cursor.toProduct())
            }
            products
        }
    }

    private fun toValues(product: Product): ContentValues {
        return ContentValues().apply {
            put("name", product.name)
            put("sku", product.sku)
            put("category", product.category)
            put("price", product.price)
            put("stock_count", product.stock)
            put("status", product.status)
            put("description", product.description)
            put("created_at", product.createdAt)
        }
    }

    private fun Cursor.toProduct(): Product {
        return Product(
            id = getInt(getColumnIndexOrThrow("id")),
            name = getString(getColumnIndexOrThrow("name")),
            sku = getString(getColumnIndexOrThrow("sku")),
            category = getString(getColumnIndexOrThrow("category")) ?: "",
            price = getDouble(getColumnIndexOrThrow("price")),
            stock = getInt(getColumnIndexOrThrow("stock_count")),
            status = getString(getColumnIndexOrThrow("status")) ?: "in_stock",
            description = getString(getColumnIndexOrThrow("description")) ?: "",
            createdAt = getLong(getColumnIndexOrThrow("created_at"))
        )
    }
}

