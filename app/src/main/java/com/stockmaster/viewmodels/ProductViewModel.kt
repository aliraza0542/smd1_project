package com.stockmaster.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.stockmaster.database.AppDatabase
import com.stockmaster.models.Product
import com.stockmaster.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository
    val allProducts: LiveData<List<Product>>
    val totalProductCount: LiveData<Int>
    val categoryCount: LiveData<Int>
    val lowStockCount: LiveData<Int>
    val outOfStockCount: LiveData<Int>
    val inStockCount: LiveData<Int>
    val totalInventoryValue: LiveData<Double?>

    init {
        val productDao = AppDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        allProducts = repository.allProducts
        totalProductCount = repository.totalProductCount
        categoryCount = repository.categoryCount
        lowStockCount = repository.lowStockCount
        outOfStockCount = repository.outOfStockCount
        inStockCount = repository.inStockCount
        totalInventoryValue = repository.totalInventoryValue
    }

    fun insert(product: Product, onComplete: ((Long) -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val id = repository.insert(product)
            onComplete?.let { callback ->
                launch(Dispatchers.Main) { callback(id) }
            }
        }
    }

    fun update(product: Product, onComplete: (() -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(product)
            onComplete?.let { callback ->
                launch(Dispatchers.Main) { callback() }
            }
        }
    }

    fun delete(product: Product, onComplete: (() -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(product)
            onComplete?.let { callback ->
                launch(Dispatchers.Main) { callback() }
            }
        }
    }

    fun updateStockQty(productId: Int, newQty: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStockQty(productId, newQty)
        }
    }

    fun getLowStockProducts(): LiveData<List<Product>> = repository.getLowStockProducts()

    fun getOutOfStockProducts(): LiveData<List<Product>> = repository.getOutOfStockProducts()

    fun getProductsByCategory(category: String): LiveData<List<Product>> =
        repository.getProductsByCategory(category)

    fun searchProducts(query: String): LiveData<List<Product>> =
        repository.searchProducts(query)

    suspend fun getById(id: Int): Product? = repository.getById(id)

    suspend fun getAllProductsList(): List<Product> = repository.getAllProductsList()
}
