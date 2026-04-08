package com.stockmaster.database;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u000e\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000bH\'J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000b2\u0006\u0010\u0014\u001a\u00020\u0011H\'J\u0010\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000bH\'J\u0010\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000bH\'J \u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000b2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H\'J \u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000b2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H\'J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H\'J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\'J$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010!J \u0010\"\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010!J \u0010#\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010!\u00a8\u0006$\u00c0\u0006\u0003"}, d2 = {"Lcom/stockmaster/database/SaleDao;", "", "insert", "", "sale", "Lcom/stockmaster/models/Sale;", "(Lcom/stockmaster/models/Sale;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "delete", "getAllSales", "Landroidx/lifecycle/LiveData;", "", "getAllSalesList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentSales", "limit", "getTotalRevenue", "", "getTotalProfit", "getTodayProfit", "startOfDay", "endOfDay", "getTodayRevenue", "getTodayTransactionCount", "getTotalSalesCount", "getSalesBetween", "startDate", "endDate", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRevenueBetween", "getProfitBetween", "app_debug"})
@androidx.room.Dao()
public abstract interface SaleDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.Sale sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.Sale sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.Sale sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sales ORDER BY saleDate DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Sale>> getAllSales();
    
    @androidx.room.Query(value = "SELECT * FROM sales ORDER BY saleDate DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllSalesList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.stockmaster.models.Sale>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sales WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.stockmaster.models.Sale> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sales ORDER BY saleDate DESC LIMIT :limit")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Sale>> getRecentSales(int limit);
    
    @androidx.room.Query(value = "SELECT SUM(totalRevenue) FROM sales")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getTotalRevenue();
    
    @androidx.room.Query(value = "SELECT SUM(totalProfit) FROM sales")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getTotalProfit();
    
    @androidx.room.Query(value = "SELECT SUM(totalProfit) FROM sales WHERE saleDate >= :startOfDay AND saleDate <= :endOfDay")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getTodayProfit(long startOfDay, long endOfDay);
    
    @androidx.room.Query(value = "SELECT SUM(totalRevenue) FROM sales WHERE saleDate >= :startOfDay AND saleDate <= :endOfDay")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getTodayRevenue(long startOfDay, long endOfDay);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM sales WHERE saleDate >= :startOfDay AND saleDate <= :endOfDay")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getTodayTransactionCount(long startOfDay, long endOfDay);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM sales")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getTotalSalesCount();
    
    @androidx.room.Query(value = "SELECT * FROM sales WHERE saleDate >= :startDate AND saleDate <= :endDate ORDER BY saleDate DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSalesBetween(long startDate, long endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.stockmaster.models.Sale>> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(totalRevenue) FROM sales WHERE saleDate >= :startDate AND saleDate <= :endDate")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRevenueBetween(long startDate, long endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(totalProfit) FROM sales WHERE saleDate >= :startDate AND saleDate <= :endDate")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProfitBetween(long startDate, long endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
}