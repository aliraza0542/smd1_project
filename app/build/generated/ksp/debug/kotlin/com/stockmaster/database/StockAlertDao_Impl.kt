package com.stockmaster.database

import androidx.lifecycle.LiveData
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.stockmaster.models.StockAlert
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class StockAlertDao_Impl(
  __db: RoomDatabase,
) : StockAlertDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfStockAlert: EntityInsertAdapter<StockAlert>

  private val __deleteAdapterOfStockAlert: EntityDeleteOrUpdateAdapter<StockAlert>

  private val __updateAdapterOfStockAlert: EntityDeleteOrUpdateAdapter<StockAlert>
  init {
    this.__db = __db
    this.__insertAdapterOfStockAlert = object : EntityInsertAdapter<StockAlert>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `stock_alerts` (`id`,`productId`,`productName`,`currentStock`,`threshold`,`alertDate`,`isRead`) VALUES (nullif(?, 0),?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: StockAlert) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.productId.toLong())
        statement.bindText(3, entity.productName)
        statement.bindLong(4, entity.currentStock.toLong())
        statement.bindLong(5, entity.threshold.toLong())
        statement.bindLong(6, entity.alertDate)
        val _tmp: Int = if (entity.isRead) 1 else 0
        statement.bindLong(7, _tmp.toLong())
      }
    }
    this.__deleteAdapterOfStockAlert = object : EntityDeleteOrUpdateAdapter<StockAlert>() {
      protected override fun createQuery(): String = "DELETE FROM `stock_alerts` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: StockAlert) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfStockAlert = object : EntityDeleteOrUpdateAdapter<StockAlert>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `stock_alerts` SET `id` = ?,`productId` = ?,`productName` = ?,`currentStock` = ?,`threshold` = ?,`alertDate` = ?,`isRead` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: StockAlert) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.productId.toLong())
        statement.bindText(3, entity.productName)
        statement.bindLong(4, entity.currentStock.toLong())
        statement.bindLong(5, entity.threshold.toLong())
        statement.bindLong(6, entity.alertDate)
        val _tmp: Int = if (entity.isRead) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        statement.bindLong(8, entity.id.toLong())
      }
    }
  }

  public override suspend fun insert(alert: StockAlert): Long = performSuspending(__db, false, true)
      { _connection ->
    val _result: Long = __insertAdapterOfStockAlert.insertAndReturnId(_connection, alert)
    _result
  }

  public override suspend fun delete(alert: StockAlert): Unit = performSuspending(__db, false, true)
      { _connection ->
    __deleteAdapterOfStockAlert.handle(_connection, alert)
  }

  public override suspend fun update(alert: StockAlert): Unit = performSuspending(__db, false, true)
      { _connection ->
    __updateAdapterOfStockAlert.handle(_connection, alert)
  }

  public override fun getAllAlerts(): LiveData<List<StockAlert>> {
    val _sql: String = "SELECT * FROM stock_alerts ORDER BY alertDate DESC"
    return __db.invalidationTracker.createLiveData(arrayOf("stock_alerts"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _cursorIndexOfProductName: Int = getColumnIndexOrThrow(_stmt, "productName")
        val _cursorIndexOfCurrentStock: Int = getColumnIndexOrThrow(_stmt, "currentStock")
        val _cursorIndexOfThreshold: Int = getColumnIndexOrThrow(_stmt, "threshold")
        val _cursorIndexOfAlertDate: Int = getColumnIndexOrThrow(_stmt, "alertDate")
        val _cursorIndexOfIsRead: Int = getColumnIndexOrThrow(_stmt, "isRead")
        val _result: MutableList<StockAlert> = mutableListOf()
        while (_stmt.step()) {
          val _item: StockAlert
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpProductId: Int
          _tmpProductId = _stmt.getLong(_cursorIndexOfProductId).toInt()
          val _tmpProductName: String
          _tmpProductName = _stmt.getText(_cursorIndexOfProductName)
          val _tmpCurrentStock: Int
          _tmpCurrentStock = _stmt.getLong(_cursorIndexOfCurrentStock).toInt()
          val _tmpThreshold: Int
          _tmpThreshold = _stmt.getLong(_cursorIndexOfThreshold).toInt()
          val _tmpAlertDate: Long
          _tmpAlertDate = _stmt.getLong(_cursorIndexOfAlertDate)
          val _tmpIsRead: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsRead).toInt()
          _tmpIsRead = _tmp != 0
          _item =
              StockAlert(_tmpId,_tmpProductId,_tmpProductName,_tmpCurrentStock,_tmpThreshold,_tmpAlertDate,_tmpIsRead)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllAlertsList(): List<StockAlert> {
    val _sql: String = "SELECT * FROM stock_alerts ORDER BY alertDate DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _cursorIndexOfProductName: Int = getColumnIndexOrThrow(_stmt, "productName")
        val _cursorIndexOfCurrentStock: Int = getColumnIndexOrThrow(_stmt, "currentStock")
        val _cursorIndexOfThreshold: Int = getColumnIndexOrThrow(_stmt, "threshold")
        val _cursorIndexOfAlertDate: Int = getColumnIndexOrThrow(_stmt, "alertDate")
        val _cursorIndexOfIsRead: Int = getColumnIndexOrThrow(_stmt, "isRead")
        val _result: MutableList<StockAlert> = mutableListOf()
        while (_stmt.step()) {
          val _item: StockAlert
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpProductId: Int
          _tmpProductId = _stmt.getLong(_cursorIndexOfProductId).toInt()
          val _tmpProductName: String
          _tmpProductName = _stmt.getText(_cursorIndexOfProductName)
          val _tmpCurrentStock: Int
          _tmpCurrentStock = _stmt.getLong(_cursorIndexOfCurrentStock).toInt()
          val _tmpThreshold: Int
          _tmpThreshold = _stmt.getLong(_cursorIndexOfThreshold).toInt()
          val _tmpAlertDate: Long
          _tmpAlertDate = _stmt.getLong(_cursorIndexOfAlertDate)
          val _tmpIsRead: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsRead).toInt()
          _tmpIsRead = _tmp != 0
          _item =
              StockAlert(_tmpId,_tmpProductId,_tmpProductName,_tmpCurrentStock,_tmpThreshold,_tmpAlertDate,_tmpIsRead)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getUnreadCount(): LiveData<Int> {
    val _sql: String = "SELECT COUNT(*) FROM stock_alerts WHERE isRead = 0"
    return __db.invalidationTracker.createLiveData(arrayOf("stock_alerts"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int?
        if (_stmt.step()) {
          val _tmp: Int?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(0).toInt()
          }
          _result = _tmp
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsRead(alertId: Int) {
    val _sql: String = "UPDATE stock_alerts SET isRead = 1 WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, alertId.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAll() {
    val _sql: String = "DELETE FROM stock_alerts"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
