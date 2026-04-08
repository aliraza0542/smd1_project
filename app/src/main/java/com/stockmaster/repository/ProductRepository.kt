package com.stockmaster.repository

import androidx.lifecycle.LiveData
import com.stockmaster.database.ProductDao
import com.stockmaster.models.Product

class ProductRepository(private val productDao: ProductDao) {

    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()
    val totalProductCount: LiveData<Int> = productDao.getTotalProductCount()
    val categoryCount: LiveData<Int> = productDao.getCategoryCount()
    val lowStockCount: LiveData<Int> = productDao.getLowStockCount()
    val outOfStockCount: LiveData<Int> = productDao.getOutOfStockCount()
    val inStockCount: LiveData<Int> = productDao.getInStockCount()
    val totalInventoryValue: LiveData<Double?> = productDao.getTotalInventoryValue()

    fun getLowStockProducts(): LiveData<List<Product>> = productDao.getLowStockProducts()

    fun getOutOfStockProducts(): LiveData<List<Product>> = productDao.getOutOfStockProducts()

    fun getProductsByCategory(category: String): LiveData<List<Product>> =
        productDao.getProductsByCategory(category)

    fun searchProducts(query: String): LiveData<List<Product>> =
        productDao.searchProducts(query)

    suspend fun insert(product: Product): Long = productDao.insert(product)

    suspend fun update(product: Product) = productDao.update(product)

    suspend fun delete(product: Product) = productDao.delete(product)

    suspend fun getById(id: Int): Product? = productDao.getById(id)

    suspend fun updateStockQty(productId: Int, newQty: Int) =
        productDao.updateStockQty(productId, newQty)

    suspend fun getAllProductsList(): List<Product> = productDao.getAllProductsList()
}
