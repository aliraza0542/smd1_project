package com.stockmaster.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.stockmaster.database.AppDatabase
import com.stockmaster.models.Sale
import com.stockmaster.models.StockAlert
import com.stockmaster.repository.SaleRepository
import com.stockmaster.utils.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaleViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SaleRepository
    private val db: AppDatabase
    val allSales: LiveData<List<Sale>>
    val totalRevenue: LiveData<Double?>
    val totalProfit: LiveData<Double?>
    val totalSalesCount: LiveData<Int>
    val todayProfit: LiveData<Double?>
    val todayRevenue: LiveData<Double?>
    val todayTransactionCount: LiveData<Int>

    init {
        db = AppDatabase.getDatabase(application)
        val saleDao = db.saleDao()
        repository = SaleRepository(saleDao)
        allSales = repository.allSales
        totalRevenue = repository.totalRevenue
        totalProfit = repository.totalProfit
        totalSalesCount = repository.totalSalesCount

        val startOfDay = DateUtils.getStartOfDay()
        val endOfDay = DateUtils.getEndOfDay()
        todayProfit = repository.getTodayProfit(startOfDay, endOfDay)
        todayRevenue = repository.getTodayRevenue(startOfDay, endOfDay)
        todayTransactionCount = repository.getTodayTransactionCount(startOfDay, endOfDay)
    }

    fun getRecentSales(limit: Int): LiveData<List<Sale>> = repository.getRecentSales(limit)

    fun recordSale(sale: Sale, onComplete: ((Long) -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val id = repository.insert(sale)
            onComplete?.let { callback ->
                launch(Dispatchers.Main) { callback(id) }
            }
        }
    }

    fun updateStock(productId: Int, newQty: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            db.productDao().updateStockQty(productId, newQty)
        }
    }

    fun insertStockAlert(alert: StockAlert) {
        viewModelScope.launch(Dispatchers.IO) {
            db.stockAlertDao().insert(alert)
        }
    }

    suspend fun getSalesBetween(startDate: Long, endDate: Long): List<Sale> =
        repository.getSalesBetween(startDate, endDate)

    suspend fun getRevenueBetween(startDate: Long, endDate: Long): Double? =
        repository.getRevenueBetween(startDate, endDate)

    suspend fun getProfitBetween(startDate: Long, endDate: Long): Double? =
        repository.getProfitBetween(startDate, endDate)

    suspend fun getAllSalesList(): List<Sale> = repository.getAllSalesList()
}
