package com.stockmaster.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.stockmaster.models.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product): Long

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT * FROM products ORDER BY dateAdded DESC")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM products ORDER BY dateAdded DESC")
    suspend fun getAllProductsList(): List<Product>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getById(id: Int): Product?

    @Query("SELECT * FROM products WHERE name LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%'")
    fun searchProducts(query: String): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE stockQuantity <= lowStockThreshold AND stockQuantity > 0")
    fun getLowStockProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE stockQuantity = 0")
    fun getOutOfStockProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE category = :category ORDER BY dateAdded DESC")
    fun getProductsByCategory(category: String): LiveData<List<Product>>

    @Query("UPDATE products SET stockQuantity = :newQty WHERE id = :productId")
    suspend fun updateStockQty(productId: Int, newQty: Int)

    @Query("SELECT COUNT(*) FROM products")
    fun getTotalProductCount(): LiveData<Int>

    @Query("SELECT COUNT(DISTINCT category) FROM products")
    fun getCategoryCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM products WHERE stockQuantity <= lowStockThreshold AND stockQuantity > 0")
    fun getLowStockCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM products WHERE stockQuantity = 0")
    fun getOutOfStockCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM products WHERE stockQuantity > lowStockThreshold")
    fun getInStockCount(): LiveData<Int>

    @Query("SELECT SUM(sellingPrice * stockQuantity) FROM products")
    fun getTotalInventoryValue(): LiveData<Double?>

    @Query("SELECT * FROM products WHERE name LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%'")
    suspend fun searchProductsList(query: String): List<Product>
}
