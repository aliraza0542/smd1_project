package com.stockmaster.repository

import androidx.lifecycle.LiveData
import com.stockmaster.database.StockAlertDao
import com.stockmaster.models.StockAlert

class StockAlertRepository(private val stockAlertDao: StockAlertDao) {

    val allAlerts: LiveData<List<StockAlert>> = stockAlertDao.getAllAlerts()
    val unreadCount: LiveData<Int> = stockAlertDao.getUnreadCount()

    suspend fun insert(alert: StockAlert): Long = stockAlertDao.insert(alert)

    suspend fun update(alert: StockAlert) = stockAlertDao.update(alert)

    suspend fun markAsRead(alertId: Int) = stockAlertDao.markAsRead(alertId)

    suspend fun deleteAll() = stockAlertDao.deleteAll()

    suspend fun getAllAlertsList(): List<StockAlert> = stockAlertDao.getAllAlertsList()
}
