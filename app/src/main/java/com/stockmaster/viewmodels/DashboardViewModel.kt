package com.stockmaster.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.stockmaster.database.AppDatabase
import com.stockmaster.models.Sale
import com.stockmaster.repository.ProductRepository
import com.stockmaster.repository.SaleRepository
import com.stockmaster.utils.DateUtils

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val productRepository: ProductRepository
    private val saleRepository: SaleRepository

    val totalProductCount: LiveData<Int>
    val categoryCount: LiveData<Int>
    val lowStockCount: LiveData<Int>
    val totalRevenue: LiveData<Double?>
    val totalProfit: LiveData<Double?>
    val todayProfit: LiveData<Double?>
    val recentSales: LiveData<List<Sale>>
    val totalInventoryValue: LiveData<Double?>

    init {
        val db = AppDatabase.getDatabase(application)
        productRepository = ProductRepository(db.productDao())
        saleRepository = SaleRepository(db.saleDao())

        totalProductCount = productRepository.totalProductCount
        categoryCount = productRepository.categoryCount
        lowStockCount = productRepository.lowStockCount
        totalRevenue = saleRepository.totalRevenue
        totalProfit = saleRepository.totalProfit
        totalInventoryValue = productRepository.totalInventoryValue

        val startOfDay = DateUtils.getStartOfDay()
        val endOfDay = DateUtils.getEndOfDay()
        todayProfit = saleRepository.getTodayProfit(startOfDay, endOfDay)
        recentSales = saleRepository.getRecentSales(5)
    }
}
