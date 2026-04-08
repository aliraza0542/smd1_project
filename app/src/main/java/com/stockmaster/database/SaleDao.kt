package com.stockmaster.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.stockmaster.models.Sale

@Dao
interface SaleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sale: Sale): Long

    @Update
    suspend fun update(sale: Sale)

    @Delete
    suspend fun delete(sale: Sale)

    @Query("SELECT * FROM sales ORDER BY saleDate DESC")
    fun getAllSales(): LiveData<List<Sale>>

    @Query("SELECT * FROM sales ORDER BY saleDate DESC")
    suspend fun getAllSalesList(): List<Sale>

    @Query("SELECT * FROM sales WHERE id = :id")
    suspend fun getById(id: Int): Sale?

    @Query("SELECT * FROM sales ORDER BY saleDate DESC LIMIT :limit")
    fun getRecentSales(limit: Int): LiveData<List<Sale>>

    @Query("SELECT SUM(totalRevenue) FROM sales")
    fun getTotalRevenue(): LiveData<Double?>

    @Query("SELECT SUM(totalProfit) FROM sales")
    fun getTotalProfit(): LiveData<Double?>

    @Query("SELECT SUM(totalProfit) FROM sales WHERE saleDate >= :startOfDay AND saleDate <= :endOfDay")
    fun getTodayProfit(startOfDay: Long, endOfDay: Long): LiveData<Double?>

    @Query("SELECT SUM(totalRevenue) FROM sales WHERE saleDate >= :startOfDay AND saleDate <= :endOfDay")
    fun getTodayRevenue(startOfDay: Long, endOfDay: Long): LiveData<Double?>

    @Query("SELECT COUNT(*) FROM sales WHERE saleDate >= :startOfDay AND saleDate <= :endOfDay")
    fun getTodayTransactionCount(startOfDay: Long, endOfDay: Long): LiveData<Int>

    @Query("SELECT COUNT(*) FROM sales")
    fun getTotalSalesCount(): LiveData<Int>

    @Query("SELECT * FROM sales WHERE saleDate >= :startDate AND saleDate <= :endDate ORDER BY saleDate DESC")
    suspend fun getSalesBetween(startDate: Long, endDate: Long): List<Sale>

    @Query("SELECT SUM(totalRevenue) FROM sales WHERE saleDate >= :startDate AND saleDate <= :endDate")
    suspend fun getRevenueBetween(startDate: Long, endDate: Long): Double?

    @Query("SELECT SUM(totalProfit) FROM sales WHERE saleDate >= :startDate AND saleDate <= :endDate")
    suspend fun getProfitBetween(startDate: Long, endDate: Long): Double?
}
