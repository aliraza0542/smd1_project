package com.stockmaster.database

import androidx.lifecycle.LiveData
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.stockmaster.models.Product
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
public class ProductDao_Impl(
  __db: RoomDatabase,
) : ProductDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfProduct: EntityInsertAdapter<Product>

  private val __deleteAdapterOfProduct: EntityDeleteOrUpdateAdapter<Product>

  private val __updateAdapterOfProduct: EntityDeleteOrUpdateAdapter<Product>
  init {
    this.__db = __db
    this.__insertAdapterOfProduct = object : EntityInsertAdapter<Product>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `products` (`id`,`name`,`category`,`description`,`purchasePrice`,`sellingPrice`,`stockQuantity`,`lowStockThreshold`,`barcode`,`dateAdded`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Product) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.category)
        statement.bindText(4, entity.description)
        statement.bindDouble(5, entity.purchasePrice)
        statement.bindDouble(6, entity.sellingPrice)
        statement.bindLong(7, entity.stockQuantity.toLong())
        statement.bindLong(8, entity.lowStockThreshold.toLong())
        statement.bindText(9, entity.barcode)
        statement.bindLong(10, entity.dateAdded)
      }
    }
    this.__deleteAdapterOfProduct = object : EntityDeleteOrUpdateAdapter<Product>() {
      protected override fun createQuery(): String = "DELETE FROM `products` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Product) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfProduct = object : EntityDeleteOrUpdateAdapter<Product>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `products` SET `id` = ?,`name` = ?,`category` = ?,`description` = ?,`purchasePrice` = ?,`sellingPrice` = ?,`stockQuantity` = ?,`lowStockThreshold` = ?,`barcode` = ?,`dateAdded` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Product) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.category)
        statement.bindText(4, entity.description)
        statement.bindDouble(5, entity.purchasePrice)
        statement.bindDouble(6, entity.sellingPrice)
        statement.bindLong(7, entity.stockQuantity.toLong())
        statement.bindLong(8, entity.lowStockThreshold.toLong())
        statement.bindText(9, entity.barcode)
        statement.bindLong(10, entity.dateAdded)
        statement.bindLong(11, entity.id.toLong())
      }
    }
  }

  public override suspend fun insert(product: Product): Long = performSuspending(__db, false, true)
      { _connection ->
    val _result: Long = __insertAdapterOfProduct.insertAndReturnId(_connection, product)
    _result
  }

  public override suspend fun delete(product: Product): Unit = performSuspending(__db, false, true)
      { _connection ->
    __deleteAdapterOfProduct.handle(_connection, product)
  }

  public override suspend fun update(product: Product): Unit = performSuspending(__db, false, true)
      { _connection ->
    __updateAdapterOfProduct.handle(_connection, product)
  }

  public override fun getAllProducts(): LiveData<List<Product>> {
    val _sql: String = "SELECT * FROM products ORDER BY dateAdded DESC"
    return __db.invalidationTracker.createLiveData(arrayOf("products"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfStockQuantity: Int = getColumnIndexOrThrow(_stmt, "stockQuantity")
        val _cursorIndexOfLowStockThreshold: Int = getColumnIndexOrThrow(_stmt, "lowStockThreshold")
        val _cursorIndexOfBarcode: Int = getColumnIndexOrThrow(_stmt, "barcode")
        val _cursorIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "dateAdded")
        val _result: MutableList<Product> = mutableListOf()
        while (_stmt.step()) {
          val _item: Product
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_cursorIndexOfCategory)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpStockQuantity: Int
          _tmpStockQuantity = _stmt.getLong(_cursorIndexOfStockQuantity).toInt()
          val _tmpLowStockThreshold: Int
          _tmpLowStockThreshold = _stmt.getLong(_cursorIndexOfLowStockThreshold).toInt()
          val _tmpBarcode: String
          _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_cursorIndexOfDateAdded)
          _item =
              Product(_tmpId,_tmpName,_tmpCategory,_tmpDescription,_tmpPurchasePrice,_tmpSellingPrice,_tmpStockQuantity,_tmpLowStockThreshold,_tmpBarcode,_tmpDateAdded)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllProductsList(): List<Product> {
    val _sql: String = "SELECT * FROM products ORDER BY dateAdded DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfStockQuantity: Int = getColumnIndexOrThrow(_stmt, "stockQuantity")
        val _cursorIndexOfLowStockThreshold: Int = getColumnIndexOrThrow(_stmt, "lowStockThreshold")
        val _cursorIndexOfBarcode: Int = getColumnIndexOrThrow(_stmt, "barcode")
        val _cursorIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "dateAdded")
        val _result: MutableList<Product> = mutableListOf()
        while (_stmt.step()) {
          val _item: Product
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_cursorIndexOfCategory)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpStockQuantity: Int
          _tmpStockQuantity = _stmt.getLong(_cursorIndexOfStockQuantity).toInt()
          val _tmpLowStockThreshold: Int
          _tmpLowStockThreshold = _stmt.getLong(_cursorIndexOfLowStockThreshold).toInt()
          val _tmpBarcode: String
          _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_cursorIndexOfDateAdded)
          _item =
              Product(_tmpId,_tmpName,_tmpCategory,_tmpDescription,_tmpPurchasePrice,_tmpSellingPrice,_tmpStockQuantity,_tmpLowStockThreshold,_tmpBarcode,_tmpDateAdded)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Int): Product? {
    val _sql: String = "SELECT * FROM products WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfStockQuantity: Int = getColumnIndexOrThrow(_stmt, "stockQuantity")
        val _cursorIndexOfLowStockThreshold: Int = getColumnIndexOrThrow(_stmt, "lowStockThreshold")
        val _cursorIndexOfBarcode: Int = getColumnIndexOrThrow(_stmt, "barcode")
        val _cursorIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "dateAdded")
        val _result: Product?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_cursorIndexOfCategory)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpStockQuantity: Int
          _tmpStockQuantity = _stmt.getLong(_cursorIndexOfStockQuantity).toInt()
          val _tmpLowStockThreshold: Int
          _tmpLowStockThreshold = _stmt.getLong(_cursorIndexOfLowStockThreshold).toInt()
          val _tmpBarcode: String
          _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_cursorIndexOfDateAdded)
          _result =
              Product(_tmpId,_tmpName,_tmpCategory,_tmpDescription,_tmpPurchasePrice,_tmpSellingPrice,_tmpStockQuantity,_tmpLowStockThreshold,_tmpBarcode,_tmpDateAdded)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchProducts(query: String): LiveData<List<Product>> {
    val _sql: String =
        "SELECT * FROM products WHERE name LIKE '%' || ? || '%' OR category LIKE '%' || ? || '%'"
    return __db.invalidationTracker.createLiveData(arrayOf("products"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, query)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfStockQuantity: Int = getColumnIndexOrThrow(_stmt, "stockQuantity")
        val _cursorIndexOfLowStockThreshold: Int = getColumnIndexOrThrow(_stmt, "lowStockThreshold")
        val _cursorIndexOfBarcode: Int = getColumnIndexOrThrow(_stmt, "barcode")
        val _cursorIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "dateAdded")
        val _result: MutableList<Product> = mutableListOf()
        while (_stmt.step()) {
          val _item: Product
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_cursorIndexOfCategory)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpStockQuantity: Int
          _tmpStockQuantity = _stmt.getLong(_cursorIndexOfStockQuantity).toInt()
          val _tmpLowStockThreshold: Int
          _tmpLowStockThreshold = _stmt.getLong(_cursorIndexOfLowStockThreshold).toInt()
          val _tmpBarcode: String
          _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_cursorIndexOfDateAdded)
          _item =
              Product(_tmpId,_tmpName,_tmpCategory,_tmpDescription,_tmpPurchasePrice,_tmpSellingPrice,_tmpStockQuantity,_tmpLowStockThreshold,_tmpBarcode,_tmpDateAdded)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getLowStockProducts(): LiveData<List<Product>> {
    val _sql: String =
        "SELECT * FROM products WHERE stockQuantity <= lowStockThreshold AND stockQuantity > 0"
    return __db.invalidationTracker.createLiveData(arrayOf("products"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfStockQuantity: Int = getColumnIndexOrThrow(_stmt, "stockQuantity")
        val _cursorIndexOfLowStockThreshold: Int = getColumnIndexOrThrow(_stmt, "lowStockThreshold")
        val _cursorIndexOfBarcode: Int = getColumnIndexOrThrow(_stmt, "barcode")
        val _cursorIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "dateAdded")
        val _result: MutableList<Product> = mutableListOf()
        while (_stmt.step()) {
          val _item: Product
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_cursorIndexOfCategory)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpStockQuantity: Int
          _tmpStockQuantity = _stmt.getLong(_cursorIndexOfStockQuantity).toInt()
          val _tmpLowStockThreshold: Int
          _tmpLowStockThreshold = _stmt.getLong(_cursorIndexOfLowStockThreshold).toInt()
          val _tmpBarcode: String
          _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_cursorIndexOfDateAdded)
          _item =
              Product(_tmpId,_tmpName,_tmpCategory,_tmpDescription,_tmpPurchasePrice,_tmpSellingPrice,_tmpStockQuantity,_tmpLowStockThreshold,_tmpBarcode,_tmpDateAdded)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getOutOfStockProducts(): LiveData<List<Product>> {
    val _sql: String = "SELECT * FROM products WHERE stockQuantity = 0"
    return __db.invalidationTracker.createLiveData(arrayOf("products"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfStockQuantity: Int = getColumnIndexOrThrow(_stmt, "stockQuantity")
        val _cursorIndexOfLowStockThreshold: Int = getColumnIndexOrThrow(_stmt, "lowStockThreshold")
        val _cursorIndexOfBarcode: Int = getColumnIndexOrThrow(_stmt, "barcode")
        val _cursorIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "dateAdded")
        val _result: MutableList<Product> = mutableListOf()
        while (_stmt.step()) {
          val _item: Product
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_cursorIndexOfCategory)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpStockQuantity: Int
          _tmpStockQuantity = _stmt.getLong(_cursorIndexOfStockQuantity).toInt()
          val _tmpLowStockThreshold: Int
          _tmpLowStockThreshold = _stmt.getLong(_cursorIndexOfLowStockThreshold).toInt()
          val _tmpBarcode: String
          _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_cursorIndexOfDateAdded)
          _item =
              Product(_tmpId,_tmpName,_tmpCategory,_tmpDescription,_tmpPurchasePrice,_tmpSellingPrice,_tmpStockQuantity,_tmpLowStockThreshold,_tmpBarcode,_tmpDateAdded)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getProductsByCategory(category: String): LiveData<List<Product>> {
    val _sql: String = "SELECT * FROM products WHERE category = ? ORDER BY dateAdded DESC"
    return __db.invalidationTracker.createLiveData(arrayOf("products"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, category)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfStockQuantity: Int = getColumnIndexOrThrow(_stmt, "stockQuantity")
        val _cursorIndexOfLowStockThreshold: Int = getColumnIndexOrThrow(_stmt, "lowStockThreshold")
        val _cursorIndexOfBarcode: Int = getColumnIndexOrThrow(_stmt, "barcode")
        val _cursorIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "dateAdded")
        val _result: MutableList<Product> = mutableListOf()
        while (_stmt.step()) {
          val _item: Product
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_cursorIndexOfCategory)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpStockQuantity: Int
          _tmpStockQuantity = _stmt.getLong(_cursorIndexOfStockQuantity).toInt()
          val _tmpLowStockThreshold: Int
          _tmpLowStockThreshold = _stmt.getLong(_cursorIndexOfLowStockThreshold).toInt()
          val _tmpBarcode: String
          _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_cursorIndexOfDateAdded)
          _item =
              Product(_tmpId,_tmpName,_tmpCategory,_tmpDescription,_tmpPurchasePrice,_tmpSellingPrice,_tmpStockQuantity,_tmpLowStockThreshold,_tmpBarcode,_tmpDateAdded)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTotalProductCount(): LiveData<Int> {
    val _sql: String = "SELECT COUNT(*) FROM products"
    return __db.invalidationTracker.createLiveData(arrayOf("products"), false) { _connection ->
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

  public override fun getCategoryCount(): LiveData<Int> {
    val _sql: String = "SELECT COUNT(DISTINCT category) FROM products"
    return __db.invalidationTracker.createLiveData(arrayOf("products"), false) { _connection ->
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

  public override fun getLowStockCount(): LiveData<Int> {
    val _sql: String =
        "SELECT COUNT(*) FROM products WHERE stockQuantity <= lowStockThreshold AND stockQuantity > 0"
    return __db.invalidationTracker.createLiveData(arrayOf("products"), false) { _connection ->
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

  public override fun getOutOfStockCount(): LiveData<Int> {
    val _sql: String = "SELECT COUNT(*) FROM products WHERE stockQuantity = 0"
    return __db.invalidationTracker.createLiveData(arrayOf("products"), false) { _connection ->
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

  public override fun getInStockCount(): LiveData<Int> {
    val _sql: String = "SELECT COUNT(*) FROM products WHERE stockQuantity > lowStockThreshold"
    return __db.invalidationTracker.createLiveData(arrayOf("products"), false) { _connection ->
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

  public override fun getTotalInventoryValue(): LiveData<Double?> {
    val _sql: String = "SELECT SUM(sellingPrice * stockQuantity) FROM products"
    return __db.invalidationTracker.createLiveData(arrayOf("products"), false) { _connection ->
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

  public override suspend fun searchProductsList(query: String): List<Product> {
    val _sql: String =
        "SELECT * FROM products WHERE name LIKE '%' || ? || '%' OR category LIKE '%' || ? || '%'"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, query)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfPurchasePrice: Int = getColumnIndexOrThrow(_stmt, "purchasePrice")
        val _cursorIndexOfSellingPrice: Int = getColumnIndexOrThrow(_stmt, "sellingPrice")
        val _cursorIndexOfStockQuantity: Int = getColumnIndexOrThrow(_stmt, "stockQuantity")
        val _cursorIndexOfLowStockThreshold: Int = getColumnIndexOrThrow(_stmt, "lowStockThreshold")
        val _cursorIndexOfBarcode: Int = getColumnIndexOrThrow(_stmt, "barcode")
        val _cursorIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "dateAdded")
        val _result: MutableList<Product> = mutableListOf()
        while (_stmt.step()) {
          val _item: Product
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_cursorIndexOfCategory)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          val _tmpPurchasePrice: Double
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice)
          val _tmpSellingPrice: Double
          _tmpSellingPrice = _stmt.getDouble(_cursorIndexOfSellingPrice)
          val _tmpStockQuantity: Int
          _tmpStockQuantity = _stmt.getLong(_cursorIndexOfStockQuantity).toInt()
          val _tmpLowStockThreshold: Int
          _tmpLowStockThreshold = _stmt.getLong(_cursorIndexOfLowStockThreshold).toInt()
          val _tmpBarcode: String
          _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_cursorIndexOfDateAdded)
          _item =
              Product(_tmpId,_tmpName,_tmpCategory,_tmpDescription,_tmpPurchasePrice,_tmpSellingPrice,_tmpStockQuantity,_tmpLowStockThreshold,_tmpBarcode,_tmpDateAdded)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateStockQty(productId: Int, newQty: Int) {
    val _sql: String = "UPDATE products SET stockQuantity = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newQty.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, productId.toLong())
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
