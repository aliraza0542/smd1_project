package com.stockmaster.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.stockmaster.models.StockAlert

@Dao
interface StockAlertDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alert: StockAlert): Long

    @Update
    suspend fun update(alert: StockAlert)

    @Delete
    suspend fun delete(alert: StockAlert)

    @Query("SELECT * FROM stock_alerts ORDER BY alertDate DESC")
    fun getAllAlerts(): LiveData<List<StockAlert>>

    @Query("SELECT * FROM stock_alerts ORDER BY alertDate DESC")
    suspend fun getAllAlertsList(): List<StockAlert>

    @Query("UPDATE stock_alerts SET isRead = 1 WHERE id = :alertId")
    suspend fun markAsRead(alertId: Int)

    @Query("DELETE FROM stock_alerts")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM stock_alerts WHERE isRead = 0")
    fun getUnreadCount(): LiveData<Int>
}
