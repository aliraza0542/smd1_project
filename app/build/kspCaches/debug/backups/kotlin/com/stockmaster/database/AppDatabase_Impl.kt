package com.stockmaster.database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _productDao: Lazy<ProductDao> = lazy {
    ProductDao_Impl(this)
  }


  private val _saleDao: Lazy<SaleDao> = lazy {
    SaleDao_Impl(this)
  }


  private val _stockAlertDao: Lazy<StockAlertDao> = lazy {
    StockAlertDao_Impl(this)
  }


  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "2b56ba1ae96c523af03ad15b4a3e7f7a", "b2e16fc011cf195b1b1199586bd10106") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `products` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `category` TEXT NOT NULL, `description` TEXT NOT NULL, `purchasePrice` REAL NOT NULL, `sellingPrice` REAL NOT NULL, `stockQuantity` INTEGER NOT NULL, `lowStockThreshold` INTEGER NOT NULL, `barcode` TEXT NOT NULL, `dateAdded` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `sales` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `productId` INTEGER NOT NULL, `productName` TEXT NOT NULL, `quantitySold` INTEGER NOT NULL, `purchasePrice` REAL NOT NULL, `sellingPrice` REAL NOT NULL, `totalRevenue` REAL NOT NULL, `totalProfit` REAL NOT NULL, `profitMarginPercent` REAL NOT NULL, `saleDate` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `stock_alerts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `productId` INTEGER NOT NULL, `productName` TEXT NOT NULL, `currentStock` INTEGER NOT NULL, `threshold` INTEGER NOT NULL, `alertDate` INTEGER NOT NULL, `isRead` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2b56ba1ae96c523af03ad15b4a3e7f7a')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `products`")
        connection.execSQL("DROP TABLE IF EXISTS `sales`")
        connection.execSQL("DROP TABLE IF EXISTS `stock_alerts`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsProducts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsProducts.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProducts.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProducts.put("category", TableInfo.Column("category", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProducts.put("description", TableInfo.Column("description", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProducts.put("purchasePrice", TableInfo.Column("purchasePrice", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProducts.put("sellingPrice", TableInfo.Column("sellingPrice", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProducts.put("stockQuantity", TableInfo.Column("stockQuantity", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProducts.put("lowStockThreshold", TableInfo.Column("lowStockThreshold", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProducts.put("barcode", TableInfo.Column("barcode", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProducts.put("dateAdded", TableInfo.Column("dateAdded", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysProducts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesProducts: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoProducts: TableInfo = TableInfo("products", _columnsProducts, _foreignKeysProducts,
            _indicesProducts)
        val _existingProducts: TableInfo = read(connection, "products")
        if (!_infoProducts.equals(_existingProducts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |products(com.stockmaster.models.Product).
              | Expected:
              |""".trimMargin() + _infoProducts + """
              |
              | Found:
              |""".trimMargin() + _existingProducts)
        }
        val _columnsSales: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSales.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSales.put("productId", TableInfo.Column("productId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSales.put("productName", TableInfo.Column("productName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSales.put("quantitySold", TableInfo.Column("quantitySold", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSales.put("purchasePrice", TableInfo.Column("purchasePrice", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSales.put("sellingPrice", TableInfo.Column("sellingPrice", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSales.put("totalRevenue", TableInfo.Column("totalRevenue", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSales.put("totalProfit", TableInfo.Column("totalProfit", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSales.put("profitMarginPercent", TableInfo.Column("profitMarginPercent", "REAL",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSales.put("saleDate", TableInfo.Column("saleDate", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSales: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSales: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSales: TableInfo = TableInfo("sales", _columnsSales, _foreignKeysSales,
            _indicesSales)
        val _existingSales: TableInfo = read(connection, "sales")
        if (!_infoSales.equals(_existingSales)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |sales(com.stockmaster.models.Sale).
              | Expected:
              |""".trimMargin() + _infoSales + """
              |
              | Found:
              |""".trimMargin() + _existingSales)
        }
        val _columnsStockAlerts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsStockAlerts.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStockAlerts.put("productId", TableInfo.Column("productId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStockAlerts.put("productName", TableInfo.Column("productName", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStockAlerts.put("currentStock", TableInfo.Column("currentStock", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStockAlerts.put("threshold", TableInfo.Column("threshold", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStockAlerts.put("alertDate", TableInfo.Column("alertDate", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStockAlerts.put("isRead", TableInfo.Column("isRead", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysStockAlerts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesStockAlerts: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoStockAlerts: TableInfo = TableInfo("stock_alerts", _columnsStockAlerts,
            _foreignKeysStockAlerts, _indicesStockAlerts)
        val _existingStockAlerts: TableInfo = read(connection, "stock_alerts")
        if (!_infoStockAlerts.equals(_existingStockAlerts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |stock_alerts(com.stockmaster.models.StockAlert).
              | Expected:
              |""".trimMargin() + _infoStockAlerts + """
              |
              | Found:
              |""".trimMargin() + _existingStockAlerts)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "products", "sales",
        "stock_alerts")
  }

  public override fun clearAllTables() {
    super.performClear(false, "products", "sales", "stock_alerts")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(ProductDao::class, ProductDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SaleDao::class, SaleDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(StockAlertDao::class, StockAlertDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun productDao(): ProductDao = _productDao.value

  public override fun saleDao(): SaleDao = _saleDao.value

  public override fun stockAlertDao(): StockAlertDao = _stockAlertDao.value
}
