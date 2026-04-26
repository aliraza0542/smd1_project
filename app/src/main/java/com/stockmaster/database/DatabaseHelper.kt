package com.stockmaster.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE products (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                sku TEXT UNIQUE NOT NULL,
                category TEXT,
                price REAL NOT NULL,
                stock_count INTEGER NOT NULL DEFAULT 0,
                status TEXT DEFAULT 'in_stock',
                description TEXT,
                created_at INTEGER NOT NULL
            )
            """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE sales (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                product_id INTEGER NOT NULL,
                quantity_sold INTEGER NOT NULL,
                sale_price REAL NOT NULL,
                sale_date INTEGER NOT NULL,
                notes TEXT,
                FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
            )
            """.trimIndent()
        )

        // Seed textile-focused inventory so first launch is immediately usable.
        db.execSQL("INSERT INTO products (name, sku, category, price, stock_count, status, description, created_at) VALUES ('Cotton Fabric Roll', 'FAB-CTN-001', 'Fabric', 79.0, 4, 'critical', 'Premium combed cotton roll', strftime('%s','now'))")
        db.execSQL("INSERT INTO products (name, sku, category, price, stock_count, status, description, created_at) VALUES ('Linen Fabric Roll', 'FAB-LIN-002', 'Fabric', 95.0, 9, 'low', 'Breathable linen material', strftime('%s','now'))")
        db.execSQL("INSERT INTO products (name, sku, category, price, stock_count, status, description, created_at) VALUES ('Denim Jeans - Blue', 'APP-DNM-003', 'Apparel', 49.0, 28, 'in_stock', 'Classic fit denim jeans', strftime('%s','now'))")
        db.execSQL("INSERT INTO products (name, sku, category, price, stock_count, status, description, created_at) VALUES ('Formal Shirt - White', 'APP-SHT-004', 'Apparel', 32.0, 18, 'in_stock', 'Wrinkle resistant shirt', strftime('%s','now'))")
        db.execSQL("INSERT INTO products (name, sku, category, price, stock_count, status, description, created_at) VALUES ('Tailor Scissors', 'ACC-TLR-005', 'Accessories', 15.5, 12, 'in_stock', 'Stainless steel tailor scissors', strftime('%s','now'))")
        db.execSQL("INSERT INTO products (name, sku, category, price, stock_count, status, description, created_at) VALUES ('Polyester Thread Pack', 'ACC-THR-006', 'Accessories', 7.99, 7, 'low', '20-spool assorted thread pack', strftime('%s','now'))")
        db.execSQL("INSERT INTO products (name, sku, category, price, stock_count, status, description, created_at) VALUES ('Khaki Trousers', 'APP-TRS-007', 'Apparel', 39.0, 2, 'critical', 'Slim fit cotton trousers', strftime('%s','now'))")
        db.execSQL("INSERT INTO products (name, sku, category, price, stock_count, status, description, created_at) VALUES ('Printed Rayon Fabric', 'FAB-RYN-008', 'Fabric', 88.0, 21, 'in_stock', 'Soft printed rayon roll', strftime('%s','now'))")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS sales")
        db.execSQL("DROP TABLE IF EXISTS products")
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "stock_master.db"
        private const val DB_VERSION = 1

        @Volatile
        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DatabaseHelper(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}

