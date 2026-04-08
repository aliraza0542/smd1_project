package com.stockmaster.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\u0006\u0010\u0015\u001a\u00020\u0012J\u001e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018J\u001e\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018J\u0016\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010!\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0018\u0010\"\u001a\u0004\u0018\u00010\t2\u0006\u0010#\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010$J\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086@\u00a2\u0006\u0002\u0010&J$\u0010\'\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010*J \u0010+\u001a\u0004\u0018\u00010\r2\u0006\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010*J \u0010,\u001a\u0004\u0018\u00010\r2\u0006\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010*R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000b\u00a8\u0006-"}, d2 = {"Lcom/stockmaster/repository/SaleRepository;", "", "saleDao", "Lcom/stockmaster/database/SaleDao;", "<init>", "(Lcom/stockmaster/database/SaleDao;)V", "allSales", "Landroidx/lifecycle/LiveData;", "", "Lcom/stockmaster/models/Sale;", "getAllSales", "()Landroidx/lifecycle/LiveData;", "totalRevenue", "", "getTotalRevenue", "totalProfit", "getTotalProfit", "totalSalesCount", "", "getTotalSalesCount", "getRecentSales", "limit", "getTodayProfit", "startOfDay", "", "endOfDay", "getTodayRevenue", "getTodayTransactionCount", "insert", "sale", "(Lcom/stockmaster/models/Sale;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "delete", "getById", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSalesList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSalesBetween", "startDate", "endDate", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRevenueBetween", "getProfitBetween", "app_debug"})
public final class SaleRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.stockmaster.database.SaleDao saleDao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Sale>> allSales = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> totalRevenue = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> totalProfit = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> totalSalesCount = null;
    
    public SaleRepository(@org.jetbrains.annotations.NotNull()
    com.stockmaster.database.SaleDao saleDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Sale>> getAllSales() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalRevenue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalProfit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getTotalSalesCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Sale>> getRecentSales(int limit) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTodayProfit(long startOfDay, long endOfDay) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTodayRevenue(long startOfDay, long endOfDay) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getTodayTransactionCount(long startOfDay, long endOfDay) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.Sale sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.Sale sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.Sale sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.stockmaster.models.Sale> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllSalesList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.stockmaster.models.Sale>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSalesBetween(long startDate, long endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.stockmaster.models.Sale>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRevenueBetween(long startDate, long endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getProfitBetween(long startDate, long endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
}