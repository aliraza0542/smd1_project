package com.stockmaster.database;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000bH\'J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\'J\u0014\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000bH\'J\u0014\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000bH\'J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000b2\u0006\u0010\u0019\u001a\u00020\u0015H\'J\u001e\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\'J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\'J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\'J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\'J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\'J\u0010\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0\u000bH\'J\u001c\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010&\u00a8\u0006\'\u00c0\u0006\u0003"}, d2 = {"Lcom/stockmaster/database/ProductDao;", "", "insert", "", "product", "Lcom/stockmaster/models/Product;", "(Lcom/stockmaster/models/Product;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "delete", "getAllProducts", "Landroidx/lifecycle/LiveData;", "", "getAllProductsList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchProducts", "query", "", "getLowStockProducts", "getOutOfStockProducts", "getProductsByCategory", "category", "updateStockQty", "productId", "newQty", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalProductCount", "getCategoryCount", "getLowStockCount", "getOutOfStockCount", "getInStockCount", "getTotalInventoryValue", "", "searchProductsList", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface ProductDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.Product product, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.Product product, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.Product product, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM products ORDER BY dateAdded DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Product>> getAllProducts();
    
    @androidx.room.Query(value = "SELECT * FROM products ORDER BY dateAdded DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllProductsList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.stockmaster.models.Product>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.stockmaster.models.Product> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE name LIKE \'%\' || :query || \'%\' OR category LIKE \'%\' || :query || \'%\'")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Product>> searchProducts(@org.jetbrains.annotations.NotNull()
    java.lang.String query);
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE stockQuantity <= lowStockThreshold AND stockQuantity > 0")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Product>> getLowStockProducts();
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE stockQuantity = 0")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Product>> getOutOfStockProducts();
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE category = :category ORDER BY dateAdded DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Product>> getProductsByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category);
    
    @androidx.room.Query(value = "UPDATE products SET stockQuantity = :newQty WHERE id = :productId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateStockQty(int productId, int newQty, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM products")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getTotalProductCount();
    
    @androidx.room.Query(value = "SELECT COUNT(DISTINCT category) FROM products")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getCategoryCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM products WHERE stockQuantity <= lowStockThreshold AND stockQuantity > 0")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getLowStockCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM products WHERE stockQuantity = 0")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getOutOfStockCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM products WHERE stockQuantity > lowStockThreshold")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getInStockCount();
    
    @androidx.room.Query(value = "SELECT SUM(sellingPrice * stockQuantity) FROM products")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getTotalInventoryValue();
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE name LIKE \'%\' || :query || \'%\' OR category LIKE \'%\' || :query || \'%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchProductsList(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.stockmaster.models.Product>> $completion);
}