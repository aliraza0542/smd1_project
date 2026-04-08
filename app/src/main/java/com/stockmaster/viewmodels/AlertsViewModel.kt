package com.stockmaster.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.stockmaster.database.AppDatabase
import com.stockmaster.models.StockAlert
import com.stockmaster.repository.StockAlertRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlertsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StockAlertRepository
    val allAlerts: LiveData<List<StockAlert>>
    val unreadCount: LiveData<Int>

    init {
        val db = AppDatabase.getDatabase(application)
        repository = StockAlertRepository(db.stockAlertDao())
        allAlerts = repository.allAlerts
        unreadCount = repository.unreadCount
    }

    fun markAsRead(alertId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.markAsRead(alertId)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}
