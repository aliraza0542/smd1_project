package com.stockmaster.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.stockmaster.models.Product
import com.stockmaster.models.Sale
import com.stockmaster.models.StockAlert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Product::class, Sale::class, StockAlert::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun saleDao(): SaleDao
    abstract fun stockAlertDao(): StockAlertDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "stockmaster_database"
                )
                    .addCallback(SeedDatabaseCallback())
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class SeedDatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    seedDatabase(database.productDao(), database.saleDao(), database.stockAlertDao())
                }
            }
        }

        private suspend fun seedDatabase(
            productDao: ProductDao,
            saleDao: SaleDao,
            stockAlertDao: StockAlertDao
        ) {
            // Seed Products
            val p1 = Product(
                name = "Wireless Headphones",
                category = "Electronics",
                description = "Premium wireless headphones with noise cancellation and 30-hour battery life.",
                purchasePrice = 3500.0,
                sellingPrice = 5500.0,
                stockQuantity = 25,
                lowStockThreshold = 5,
                barcode = "ELC-001",
                dateAdded = System.currentTimeMillis() - 86400000L * 10
            )
            val p2 = Product(
                name = "USB-C Hub",
                category = "Electronics",
                description = "7-in-1 USB-C hub with HDMI, USB 3.0, and SD card reader.",
                purchasePrice = 1200.0,
                sellingPrice = 2200.0,
                stockQuantity = 8,
                lowStockThreshold = 10,
                barcode = "ELC-002",
                dateAdded = System.currentTimeMillis() - 86400000L * 8
            )
            val p3 = Product(
                name = "Bluetooth Speaker",
                category = "Electronics",
                description = "Portable Bluetooth speaker with deep bass and waterproof design.",
                purchasePrice = 2800.0,
                sellingPrice = 4500.0,
                stockQuantity = 15,
                lowStockThreshold = 5,
                barcode = "ELC-003",
                dateAdded = System.currentTimeMillis() - 86400000L * 7
            )
            val p4 = Product(
                name = "Organic Green Tea",
                category = "Food",
                description = "100% organic green tea leaves, sourced from premium estates.",
                purchasePrice = 450.0,
                sellingPrice = 850.0,
                stockQuantity = 60,
                lowStockThreshold = 15,
                barcode = "FD-001",
                dateAdded = System.currentTimeMillis() - 86400000L * 6
            )
            val p5 = Product(
                name = "Protein Bars (Box)",
                category = "Food",
                description = "Box of 12 protein bars, 20g protein each, chocolate flavor.",
                purchasePrice = 1800.0,
                sellingPrice = 2800.0,
                stockQuantity = 3,
                lowStockThreshold = 10,
                barcode = "FD-002",
                dateAdded = System.currentTimeMillis() - 86400000L * 5
            )
            val p6 = Product(
                name = "Coffee Beans 1kg",
                category = "Food",
                description = "Premium arabica coffee beans, medium roast, 1kg pack.",
                purchasePrice = 900.0,
                sellingPrice = 1600.0,
                stockQuantity = 0,
                lowStockThreshold = 5,
                barcode = "FD-003",
                dateAdded = System.currentTimeMillis() - 86400000L * 4
            )
            val p7 = Product(
                name = "Cotton T-Shirt (M)",
                category = "Clothing",
                description = "100% cotton t-shirt, medium size, available in multiple colors.",
                purchasePrice = 500.0,
                sellingPrice = 999.0,
                stockQuantity = 45,
                lowStockThreshold = 10,
                barcode = "CLT-001",
                dateAdded = System.currentTimeMillis() - 86400000L * 3
            )
            val p8 = Product(
                name = "Denim Jacket",
                category = "Clothing",
                description = "Classic denim jacket with button closure and chest pockets.",
                purchasePrice = 2200.0,
                sellingPrice = 3999.0,
                stockQuantity = 12,
                lowStockThreshold = 5,
                barcode = "CLT-002",
                dateAdded = System.currentTimeMillis() - 86400000L * 2
            )

            val id1 = productDao.insert(p1)
            val id2 = productDao.insert(p2)
            val id3 = productDao.insert(p3)
            val id4 = productDao.insert(p4)
            val id5 = productDao.insert(p5)
            val id6 = productDao.insert(p6)
            val id7 = productDao.insert(p7)
            val id8 = productDao.insert(p8)

            // Seed Sales (5 sales over last 7 days)
            val now = System.currentTimeMillis()
            val s1 = Sale(
                productId = id1.toInt(),
                productName = "Wireless Headphones",
                quantitySold = 2,
                purchasePrice = 3500.0,
                sellingPrice = 5500.0,
                totalRevenue = 11000.0,
                totalProfit = 4000.0,
                profitMarginPercent = 36.36,
                saleDate = now - 86400000L * 6
            )
            val s2 = Sale(
                productId = id4.toInt(),
                productName = "Organic Green Tea",
                quantitySold = 5,
                purchasePrice = 450.0,
                sellingPrice = 850.0,
                totalRevenue = 4250.0,
                totalProfit = 2000.0,
                profitMarginPercent = 47.06,
                saleDate = now - 86400000L * 4
            )
            val s3 = Sale(
                productId = id7.toInt(),
                productName = "Cotton T-Shirt (M)",
                quantitySold = 3,
                purchasePrice = 500.0,
                sellingPrice = 999.0,
                totalRevenue = 2997.0,
                totalProfit = 1497.0,
                profitMarginPercent = 49.95,
                saleDate = now - 86400000L * 3
            )
            val s4 = Sale(
                productId = id3.toInt(),
                productName = "Bluetooth Speaker",
                quantitySold = 1,
                purchasePrice = 2800.0,
                sellingPrice = 4500.0,
                totalRevenue = 4500.0,
                totalProfit = 1700.0,
                profitMarginPercent = 37.78,
                saleDate = now - 86400000L * 1
            )
            val s5 = Sale(
                productId = id8.toInt(),
                productName = "Denim Jacket",
                quantitySold = 2,
                purchasePrice = 2200.0,
                sellingPrice = 3999.0,
                totalRevenue = 7998.0,
                totalProfit = 3598.0,
                profitMarginPercent = 44.99,
                saleDate = now
            )

            saleDao.insert(s1)
            saleDao.insert(s2)
            saleDao.insert(s3)
            saleDao.insert(s4)
            saleDao.insert(s5)

            // Seed Stock Alerts for low stock items
            val sa1 = StockAlert(
                productId = id2.toInt(),
                productName = "USB-C Hub",
                currentStock = 8,
                threshold = 10,
                alertDate = now,
                isRead = false
            )
            val sa2 = StockAlert(
                productId = id5.toInt(),
                productName = "Protein Bars (Box)",
                currentStock = 3,
                threshold = 10,
                alertDate = now,
                isRead = false
            )

            stockAlertDao.insert(sa1)
            stockAlertDao.insert(sa2)
        }
    }
}
