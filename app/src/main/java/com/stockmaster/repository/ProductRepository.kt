package com.stockmaster.repository

import com.stockmaster.database.dao.ProductDao
import com.stockmaster.database.dao.SaleDao
import com.stockmaster.models.CatalogProduct
import com.stockmaster.models.Product
import com.stockmaster.models.Sale
import com.stockmaster.models.SalesSummary
import com.stockmaster.network.ApiService

class ProductRepository(
    private val productDao: ProductDao,
    private val saleDao: SaleDao,
    private val apiService: ApiService
) {

    suspend fun getLocalProducts(
        query: String,
        statusFilter: String,
        sortOption: String
    ): List<Product> {
        val base = when {
            query.isNotBlank() -> productDao.searchProducts(query)
            statusFilter != "all" -> productDao.filterByStatus(statusFilter)
            sortOption == SORT_STOCK_ASC -> productDao.sortByStockAscending()
            sortOption == SORT_STOCK_DESC -> productDao.sortByStockDescending()
            sortOption == SORT_PRICE_DESC -> productDao.sortByPriceDescending()
            sortOption == SORT_PRICE_ASC -> productDao.sortByPriceAscending()
            else -> productDao.getAllProducts()
        }

        val filteredByStatus = if (statusFilter == "all") base else base.filter { it.status == statusFilter }
        return when (sortOption) {
            SORT_NAME_ASC -> filteredByStatus.sortedBy { it.name.lowercase() }
            SORT_STOCK_ASC -> filteredByStatus.sortedBy { it.stock }
            SORT_STOCK_DESC -> filteredByStatus.sortedByDescending { it.stock }
            SORT_PRICE_ASC -> filteredByStatus.sortedBy { it.price }
            SORT_PRICE_DESC -> filteredByStatus.sortedByDescending { it.price }
            else -> filteredByStatus
        }
    }

    suspend fun getApiProducts(limit: Int = 30): List<CatalogProduct> {
        return apiService.getProducts(limit).products
    }

    suspend fun addProduct(product: Product): Long = productDao.insertProduct(product)

    suspend fun updateProduct(product: Product): Int = productDao.updateProduct(product)

    suspend fun deleteProduct(id: Int): Int = productDao.deleteProduct(id)

    suspend fun getProductById(id: Int): Product? = productDao.getProductById(id)

    suspend fun recordSale(product: Product, quantitySold: Int): Boolean {
        if (quantitySold <= 0 || quantitySold > product.stock) return false
        val saleInserted = saleDao.insertSale(
            Sale(
                productId = product.id,
                quantitySold = quantitySold,
                salePrice = product.price,
                notes = "Recorded from Product Detail"
            )
        )
        if (saleInserted <= 0) return false

        val newStock = product.stock - quantitySold
        val newStatus = when {
            newStock < 5 -> "critical"
            newStock < 10 -> "low"
            else -> "in_stock"
        }
        return productDao.updateProduct(product.copy(stock = newStock, status = newStatus)) > 0
    }

    suspend fun getLowStockCount(): Int = productDao.getLowStockCount(10)

    suspend fun getSalesSummaryPerProduct(): List<SalesSummary> = productDao.getSalesSummaryPerProduct()

    suspend fun getAllSales(): List<Sale> = saleDao.getAllSales()

    companion object {
        const val SORT_NAME_ASC = "name_asc"
        const val SORT_STOCK_ASC = "stock_asc"
        const val SORT_STOCK_DESC = "stock_desc"
        const val SORT_PRICE_ASC = "price_asc"
        const val SORT_PRICE_DESC = "price_desc"
    }
}

