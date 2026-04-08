package com.stockmaster.database;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u0000 \n2\u00020\u0001:\u0002\n\u000bB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&\u00a8\u0006\f"}, d2 = {"Lcom/stockmaster/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "productDao", "Lcom/stockmaster/database/ProductDao;", "saleDao", "Lcom/stockmaster/database/SaleDao;", "stockAlertDao", "Lcom/stockmaster/database/StockAlertDao;", "Companion", "SeedDatabaseCallback", "app_debug"})
@androidx.room.Database(entities = {com.stockmaster.models.Product.class, com.stockmaster.models.Sale.class, com.stockmaster.models.StockAlert.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.stockmaster.database.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.stockmaster.database.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.stockmaster.database.ProductDao productDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.stockmaster.database.SaleDao saleDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.stockmaster.database.StockAlertDao stockAlertDao();
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/stockmaster/database/AppDatabase$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/stockmaster/database/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.stockmaster.database.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J&\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0082@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/stockmaster/database/AppDatabase$SeedDatabaseCallback;", "Landroidx/room/RoomDatabase$Callback;", "<init>", "()V", "onCreate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "seedDatabase", "productDao", "Lcom/stockmaster/database/ProductDao;", "saleDao", "Lcom/stockmaster/database/SaleDao;", "stockAlertDao", "Lcom/stockmaster/database/StockAlertDao;", "(Lcom/stockmaster/database/ProductDao;Lcom/stockmaster/database/SaleDao;Lcom/stockmaster/database/StockAlertDao;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
    static final class SeedDatabaseCallback extends androidx.room.RoomDatabase.Callback {
        
        public SeedDatabaseCallback() {
            super();
        }
        
        @java.lang.Override()
        public void onCreate(@org.jetbrains.annotations.NotNull()
        androidx.sqlite.db.SupportSQLiteDatabase db) {
        }
        
        private final java.lang.Object seedDatabase(com.stockmaster.database.ProductDao productDao, com.stockmaster.database.SaleDao saleDao, com.stockmaster.database.StockAlertDao stockAlertDao, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
    }
}