package com.stockmaster.database

import androidx.lifecycle.LiveData
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.stockmaster.models.Sale
import javax.`annotation`.processing.Generated
import kotlin.Double
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
public class SaleDao_Impl(
  __db: RoomDatabase,
) : SaleDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSale: EntityInsertAdapter<Sale>

  private val __deleteAdapterOfSale: EntityDeleteOrUpdateAdapter<Sale>

  private val __updateAdapterOfSale: EntityDeleteOrUpdateAdapter<Sale>
  init {
    this.__db = __db
    this.__insertAdapterOfSale = object : EntityInsertAdapter<Sale>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `sales` (`id`,`productId`,`productName`,`quantitySold`,`purchasePrice`,`sellingPrice`,`totalRevenue`,`totalProfit`,`profitMarginPercent`,`saleDate`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Sale) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.productId.toLong())
        statement.bindText(3, entity.productName)
        statement.bindLong(4, entity.quantitySold.toLong())
        statement.bindDouble(5, entity.purchasePrice)
        statement.bindDouble(6, entity.sellingPrice)
        statement.bindDouble(7, entity.totalRevenue)
        statement.bindDouble(8, entity.totalProfit)
        statement.bindDouble(9, entity.profitMarginPercent)
        statement.bindLong(10, entity.saleDate)
      }
    }
    this.__deleteAdapterOfSale = object : EntityDeleteOrUpdateAdapter<Sale>() {
      protected override fun createQuery(): String = "DELETE FROM `sales` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Sale) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfSale = object : EntityDeleteOrUpdateAdapter<Sale>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `sales` SET `id` = ?,`productId` = ?,`productName` = ?,`quantitySold` = ?,`purchasePrice` = ?,`sellingPrice` = ?,`totalRevenue` = ?,`totalProfit` = ?,`profitMarginPercent` = ?,`saleDate` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Sale) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.productId.toLong())
        statement.bindText(3, entity.productName)
        statement.bindLong(4, entity.quantitySold.toLong())
        statement.bindDouble(5, entity.purchasePrice)
        statement.bindDouble(6, entity.sellingPrice)
        statement.bindDouble(7, entity.totalRevenue)
        statement.bindDouble(8, entity.totalProfit)
        statement.bindDouble(9, entity.profitMarginPercent)
        statement.bindLong(10, entity.saleDate)
        statement.bindLong(11, entity.id.toLong())
      }
    }
  }

  public override suspend fun insert(sale: Sale): Long = performSuspending(__db, false, true) {
      _connection ->
    val _result: Long = __insertAdapterOfSale.insertAndReturnId(_connection, sale)
    _result
  }

  public override suspend fun delete(sale: Sale): Unit = performSuspending(__db, false, true) {
      _connection ->
    __deleteAdapterOfSale.handle(_connection, sale)
  }

  public override suspend fun update(sale: Sale): Unit = performSuspending(__db, false, true) {
      _connection ->
    __updateAdapterOfSale.handle(_connection, sale)
  }

  public override fun getAllSales(): LiveData<List<Sale>> {
    val _sql: String = "SELECT * FROM sales ORDER BY saleDate DESC"
    return __db.invalidationTracker.createLiveData(arrayOf("sales"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _cursorIndexOfProductName: Int = getColumnIndexOrThrow(_stmt, "productName")
        val _cursorIndexOfQuantitySold: Int = getColumnIndexOrThrow(_stmt, "quantitySold")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfTotalRevenue: Int = getColumnIndexOrThrow(_stmt, "totalRevenue")
        val _cursorIndexOfTotalProfit: Int = getColumnIndexOrThrow(_stmt, "totalProfit")
        val _cursorIndexOfProfitMarginPercent: Int = getColumnIndexOrThrow(_stmt,
            "profitMarginPercent")
        val _cursorIndexOfSaleDate: Int = getColumnIndexOrThrow(_stmt, "saleDate")
        val _result: MutableList<Sale> = mutableListOf()
        while (_stmt.step()) {
          val _item: Sale
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpProductId: Int
          _tmpProductId = _stmt.getLong(_cursorIndexOfProductId).toInt()
          val _tmpProductName: String
          _tmpProductName = _stmt.getText(_cursorIndexOfProductName)
          val _tmpQuantitySold: Int
          _tmpQuantitySold = _stmt.getLong(_cursorIndexOfQuantitySold).toInt()
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpTotalRevenue: Double
          _tmpTotalRevenue = _stmt.getDouble(_cursorIndexOfTotalRevenue)
          val _tmpTotalProfit: Double
          _tmpTotalProfit = _stmt.getDouble(_cursorIndexOfTotalProfit)
          val _tmpProfitMarginPercent: Double
          _tmpProfitMarginPercent = _stmt.getDouble(_cursorIndexOfProfitMarginPercent)
          val _tmpSaleDate: Long
          _tmpSaleDate = _stmt.getLong(_cursorIndexOfSaleDate)
          _item =
              Sale(_tmpId,_tmpProductId,_tmpProductName,_tmpQuantitySold,_tmpPurchasePrice,_tmpSellingPrice,_tmpTotalRevenue,_tmpTotalProfit,_tmpProfitMarginPercent,_tmpSaleDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSalesList(): List<Sale> {
    val _sql: String = "SELECT * FROM sales ORDER BY saleDate DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _cursorIndexOfProductName: Int = getColumnIndexOrThrow(_stmt, "productName")
        val _cursorIndexOfQuantitySold: Int = getColumnIndexOrThrow(_stmt, "quantitySold")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfTotalRevenue: Int = getColumnIndexOrThrow(_stmt, "totalRevenue")
        val _cursorIndexOfTotalProfit: Int = getColumnIndexOrThrow(_stmt, "totalProfit")
        val _cursorIndexOfProfitMarginPercent: Int = getColumnIndexOrThrow(_stmt,
            "profitMarginPercent")
        val _cursorIndexOfSaleDate: Int = getColumnIndexOrThrow(_stmt, "saleDate")
        val _result: MutableList<Sale> = mutableListOf()
        while (_stmt.step()) {
          val _item: Sale
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpProductId: Int
          _tmpProductId = _stmt.getLong(_cursorIndexOfProductId).toInt()
          val _tmpProductName: String
          _tmpProductName = _stmt.getText(_cursorIndexOfProductName)
          val _tmpQuantitySold: Int
          _tmpQuantitySold = _stmt.getLong(_cursorIndexOfQuantitySold).toInt()
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpTotalRevenue: Double
          _tmpTotalRevenue = _stmt.getDouble(_cursorIndexOfTotalRevenue)
          val _tmpTotalProfit: Double
          _tmpTotalProfit = _stmt.getDouble(_cursorIndexOfTotalProfit)
          val _tmpProfitMarginPercent: Double
          _tmpProfitMarginPercent = _stmt.getDouble(_cursorIndexOfProfitMarginPercent)
          val _tmpSaleDate: Long
          _tmpSaleDate = _stmt.getLong(_cursorIndexOfSaleDate)
          _item =
              Sale(_tmpId,_tmpProductId,_tmpProductName,_tmpQuantitySold,_tmpPurchasePrice,_tmpSellingPrice,_tmpTotalRevenue,_tmpTotalProfit,_tmpProfitMarginPercent,_tmpSaleDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Int): Sale? {
    val _sql: String = "SELECT * FROM sales WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _cursorIndexOfProductName: Int = getColumnIndexOrThrow(_stmt, "productName")
        val _cursorIndexOfQuantitySold: Int = getColumnIndexOrThrow(_stmt, "quantitySold")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfTotalRevenue: Int = getColumnIndexOrThrow(_stmt, "totalRevenue")
        val _cursorIndexOfTotalProfit: Int = getColumnIndexOrThrow(_stmt, "totalProfit")
        val _cursorIndexOfProfitMarginPercent: Int = getColumnIndexOrThrow(_stmt,
            "profitMarginPercent")
        val _cursorIndexOfSaleDate: Int = getColumnIndexOrThrow(_stmt, "saleDate")
        val _result: Sale?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpProductId: Int
          _tmpProductId = _stmt.getLong(_cursorIndexOfProductId).toInt()
          val _tmpProductName: String
          _tmpProductName = _stmt.getText(_cursorIndexOfProductName)
          val _tmpQuantitySold: Int
          _tmpQuantitySold = _stmt.getLong(_cursorIndexOfQuantitySold).toInt()
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpTotalRevenue: Double
          _tmpTotalRevenue = _stmt.getDouble(_cursorIndexOfTotalRevenue)
          val _tmpTotalProfit: Double
          _tmpTotalProfit = _stmt.getDouble(_cursorIndexOfTotalProfit)
          val _tmpProfitMarginPercent: Double
          _tmpProfitMarginPercent = _stmt.getDouble(_cursorIndexOfProfitMarginPercent)
          val _tmpSaleDate: Long
          _tmpSaleDate = _stmt.getLong(_cursorIndexOfSaleDate)
          _result =
              Sale(_tmpId,_tmpProductId,_tmpProductName,_tmpQuantitySold,_tmpPurchasePrice,_tmpSellingPrice,_tmpTotalRevenue,_tmpTotalProfit,_tmpProfitMarginPercent,_tmpSaleDate)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRecentSales(limit: Int): LiveData<List<Sale>> {
    val _sql: String = "SELECT * FROM sales ORDER BY saleDate DESC LIMIT ?"
    return __db.invalidationTracker.createLiveData(arrayOf("sales"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, limit.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _cursorIndexOfProductName: Int = getColumnIndexOrThrow(_stmt, "productName")
        val _cursorIndexOfQuantitySold: Int = getColumnIndexOrThrow(_stmt, "quantitySold")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfTotalRevenue: Int = getColumnIndexOrThrow(_stmt, "totalRevenue")
        val _cursorIndexOfTotalProfit: Int = getColumnIndexOrThrow(_stmt, "totalProfit")
        val _cursorIndexOfProfitMarginPercent: Int = getColumnIndexOrThrow(_stmt,
            "profitMarginPercent")
        val _cursorIndexOfSaleDate: Int = getColumnIndexOrThrow(_stmt, "saleDate")
        val _result: MutableList<Sale> = mutableListOf()
        while (_stmt.step()) {
          val _item: Sale
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpProductId: Int
          _tmpProductId = _stmt.getLong(_cursorIndexOfProductId).toInt()
          val _tmpProductName: String
          _tmpProductName = _stmt.getText(_cursorIndexOfProductName)
          val _tmpQuantitySold: Int
          _tmpQuantitySold = _stmt.getLong(_cursorIndexOfQuantitySold).toInt()
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpTotalRevenue: Double
          _tmpTotalRevenue = _stmt.getDouble(_cursorIndexOfTotalRevenue)
          val _tmpTotalProfit: Double
          _tmpTotalProfit = _stmt.getDouble(_cursorIndexOfTotalProfit)
          val _tmpProfitMarginPercent: Double
          _tmpProfitMarginPercent = _stmt.getDouble(_cursorIndexOfProfitMarginPercent)
          val _tmpSaleDate: Long
          _tmpSaleDate = _stmt.getLong(_cursorIndexOfSaleDate)
          _item =
              Sale(_tmpId,_tmpProductId,_tmpProductName,_tmpQuantitySold,_tmpPurchasePrice,_tmpSellingPrice,_tmpTotalRevenue,_tmpTotalProfit,_tmpProfitMarginPercent,_tmpSaleDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTotalRevenue(): LiveData<Double?> {
    val _sql: String = "SELECT SUM(totalRevenue) FROM sales"
    return __db.invalidationTracker.createLiveData(arrayOf("sales"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Double?
        if (_stmt.step()) {
          val _tmp: Double?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getDouble(0)
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

  public override fun getTotalProfit(): LiveData<Double?> {
    val _sql: String = "SELECT SUM(totalProfit) FROM sales"
    return __db.invalidationTracker.createLiveData(arrayOf("sales"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Double?
        if (_stmt.step()) {
          val _tmp: Double?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getDouble(0)
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

  public override fun getTodayProfit(startOfDay: Long, endOfDay: Long): LiveData<Double?> {
    val _sql: String = "SELECT SUM(totalProfit) FROM sales WHERE saleDate >= ? AND saleDate <= ?"
    return __db.invalidationTracker.createLiveData(arrayOf("sales"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, startOfDay)
        _argIndex = 2
        _stmt.bindLong(_argIndex, endOfDay)
        val _result: Double?
        if (_stmt.step()) {
          val _tmp: Double?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getDouble(0)
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

  public override fun getTodayRevenue(startOfDay: Long, endOfDay: Long): LiveData<Double?> {
    val _sql: String = "SELECT SUM(totalRevenue) FROM sales WHERE saleDate >= ? AND saleDate <= ?"
    return __db.invalidationTracker.createLiveData(arrayOf("sales"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, startOfDay)
        _argIndex = 2
        _stmt.bindLong(_argIndex, endOfDay)
        val _result: Double?
        if (_stmt.step()) {
          val _tmp: Double?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getDouble(0)
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

  public override fun getTodayTransactionCount(startOfDay: Long, endOfDay: Long): LiveData<Int> {
    val _sql: String = "SELECT COUNT(*) FROM sales WHERE saleDate >= ? AND saleDate <= ?"
    return __db.invalidationTracker.createLiveData(arrayOf("sales"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, startOfDay)
        _argIndex = 2
        _stmt.bindLong(_argIndex, endOfDay)
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

  public override fun getTotalSalesCount(): LiveData<Int> {
    val _sql: String = "SELECT COUNT(*) FROM sales"
    return __db.invalidationTracker.createLiveData(arrayOf("sales"), false) { _connection ->
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

  public override suspend fun getSalesBetween(startDate: Long, endDate: Long): List<Sale> {
    val _sql: String =
        "SELECT * FROM sales WHERE saleDate >= ? AND saleDate <= ? ORDER BY saleDate DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, startDate)
        _argIndex = 2
        _stmt.bindLong(_argIndex, endDate)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _cursorIndexOfProductName: Int = getColumnIndexOrThrow(_stmt, "productName")
        val _cursorIndexOfQuantitySold: Int = getColumnIndexOrThrow(_stmt, "quantitySold")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfTotalRevenue: Int = getColumnIndexOrThrow(_stmt, "totalRevenue")
        val _cursorIndexOfTotalProfit: Int = getColumnIndexOrThrow(_stmt, "totalProfit")
        val _cursorIndexOfProfitMarginPercent: Int = getColumnIndexOrThrow(_stmt,
            "profitMarginPercent")
        val _cursorIndexOfSaleDate: Int = getColumnIndexOrThrow(_stmt, "saleDate")
        val _result: MutableList<Sale> = mutableListOf()
        while (_stmt.step()) {
          val _item: Sale
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpProductId: Int
          _tmpProductId = _stmt.getLong(_cursorIndexOfProductId).toInt()
          val _tmpProductName: String
          _tmpProductName = _stmt.getText(_cursorIndexOfProductName)
          val _tmpQuantitySold: Int
          _tmpQuantitySold = _stmt.getLong(_cursorIndexOfQuantitySold).toInt()
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpTotalRevenue: Double
          _tmpTotalRevenue = _stmt.getDouble(_cursorIndexOfTotalRevenue)
          val _tmpTotalProfit: Double
          _tmpTotalProfit = _stmt.getDouble(_cursorIndexOfTotalProfit)
          val _tmpProfitMarginPercent: Double
          _tmpProfitMarginPercent = _stmt.getDouble(_cursorIndexOfProfitMarginPercent)
          val _tmpSaleDate: Long
          _tmpSaleDate = _stmt.getLong(_cursorIndexOfSaleDate)
          _item =
              Sale(_tmpId,_tmpProductId,_tmpProductName,_tmpQuantitySold,_tmpPurchasePrice,_tmpSellingPrice,_tmpTotalRevenue,_tmpTotalProfit,_tmpProfitMarginPercent,_tmpSaleDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRevenueBetween(startDate: Long, endDate: Long): Double? {
    val _sql: String = "SELECT SUM(totalRevenue) FROM sales WHERE saleDate >= ? AND saleDate <= ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, startDate)
        _argIndex = 2
        _stmt.bindLong(_argIndex, endDate)
        val _result: Double?
        if (_stmt.step()) {
          val _tmp: Double?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getDouble(0)
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

  public override suspend fun getProfitBetween(startDate: Long, endDate: Long): Double? {
    val _sql: String = "SELECT SUM(totalProfit) FROM sales WHERE saleDate >= ? AND saleDate <= ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, startDate)
        _argIndex = 2
        _stmt.bindLong(_argIndex, endDate)
        val _result: Double?
        if (_stmt.step()) {
          val _tmp: Double?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getDouble(0)
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

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
