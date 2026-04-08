package com.stockmaster.viewmodels;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b2\u0006\u0010\u001f\u001a\u00020\u0016J&\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\r2\u0016\b\u0002\u0010#\u001a\u0010\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020!\u0018\u00010$J\u0016\u0010&\u001a\u00020!2\u0006\u0010\'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020\u0016J\u000e\u0010)\u001a\u00020!2\u0006\u0010*\u001a\u00020+J$\u0010,\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020%H\u0086@\u00a2\u0006\u0002\u0010/J \u00100\u001a\u0004\u0018\u00010\u00112\u0006\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020%H\u0086@\u00a2\u0006\u0002\u0010/J \u00101\u001a\u0004\u0018\u00010\u00112\u0006\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020%H\u0086@\u00a2\u0006\u0002\u0010/J\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0086@\u00a2\u0006\u0002\u00103R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0019\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000fR\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000fR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000f\u00a8\u00064"}, d2 = {"Lcom/stockmaster/viewmodels/SaleViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "repository", "Lcom/stockmaster/repository/SaleRepository;", "db", "Lcom/stockmaster/database/AppDatabase;", "allSales", "Landroidx/lifecycle/LiveData;", "", "Lcom/stockmaster/models/Sale;", "getAllSales", "()Landroidx/lifecycle/LiveData;", "totalRevenue", "", "getTotalRevenue", "totalProfit", "getTotalProfit", "totalSalesCount", "", "getTotalSalesCount", "todayProfit", "getTodayProfit", "todayRevenue", "getTodayRevenue", "todayTransactionCount", "getTodayTransactionCount", "getRecentSales", "limit", "recordSale", "", "sale", "onComplete", "Lkotlin/Function1;", "", "updateStock", "productId", "newQty", "insertStockAlert", "alert", "Lcom/stockmaster/models/StockAlert;", "getSalesBetween", "startDate", "endDate", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRevenueBetween", "getProfitBetween", "getAllSalesList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SaleViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.stockmaster.repository.SaleRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.stockmaster.database.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Sale>> allSales = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> totalRevenue = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> totalProfit = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> totalSalesCount = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> todayProfit = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> todayRevenue = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> todayTransactionCount = null;
    
    public SaleViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
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
    public final androidx.lifecycle.LiveData<java.lang.Double> getTodayProfit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTodayRevenue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getTodayTransactionCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.Sale>> getRecentSales(int limit) {
        return null;
    }
    
    public final void recordSale(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.Sale sale, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onComplete) {
    }
    
    public final void updateStock(int productId, int newQty) {
    }
    
    public final void insertStockAlert(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.StockAlert alert) {
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllSalesList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.stockmaster.models.Sale>> $completion) {
        return null;
    }
}