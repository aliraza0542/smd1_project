package com.stockmaster.repository

import androidx.lifecycle.LiveData
import com.stockmaster.database.SaleDao
import com.stockmaster.models.Sale

class SaleRepository(private val saleDao: SaleDao) {

    val allSales: LiveData<List<Sale>> = saleDao.getAllSales()
    val totalRevenue: LiveData<Double?> = saleDao.getTotalRevenue()
    val totalProfit: LiveData<Double?> = saleDao.getTotalProfit()
    val totalSalesCount: LiveData<Int> = saleDao.getTotalSalesCount()

    fun getRecentSales(limit: Int): LiveData<List<Sale>> = saleDao.getRecentSales(limit)

    fun getTodayProfit(startOfDay: Long, endOfDay: Long): LiveData<Double?> =
        saleDao.getTodayProfit(startOfDay, endOfDay)

    fun getTodayRevenue(startOfDay: Long, endOfDay: Long): LiveData<Double?> =
        saleDao.getTodayRevenue(startOfDay, endOfDay)

    fun getTodayTransactionCount(startOfDay: Long, endOfDay: Long): LiveData<Int> =
        saleDao.getTodayTransactionCount(startOfDay, endOfDay)

    suspend fun insert(sale: Sale): Long = saleDao.insert(sale)

    suspend fun update(sale: Sale) = saleDao.update(sale)

    suspend fun delete(sale: Sale) = saleDao.delete(sale)

    suspend fun getById(id: Int): Sale? = saleDao.getById(id)

    suspend fun getAllSalesList(): List<Sale> = saleDao.getAllSalesList()

    suspend fun getSalesBetween(startDate: Long, endDate: Long): List<Sale> =
        saleDao.getSalesBetween(startDate, endDate)

    suspend fun getRevenueBetween(startDate: Long, endDate: Long): Double? =
        saleDao.getRevenueBetween(startDate, endDate)

    suspend fun getProfitBetween(startDate: Long, endDate: Long): Double? =
        saleDao.getProfitBetween(startDate, endDate)
}
