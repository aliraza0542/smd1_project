package com.stockmaster.database;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000bH\'J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\'\u00a8\u0006\u0015\u00c0\u0006\u0003"}, d2 = {"Lcom/stockmaster/database/StockAlertDao;", "", "insert", "", "alert", "Lcom/stockmaster/models/StockAlert;", "(Lcom/stockmaster/models/StockAlert;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "delete", "getAllAlerts", "Landroidx/lifecycle/LiveData;", "", "getAllAlertsList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsRead", "alertId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "getUnreadCount", "app_debug"})
@androidx.room.Dao()
public abstract interface StockAlertDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.StockAlert alert, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.StockAlert alert, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.stockmaster.models.StockAlert alert, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM stock_alerts ORDER BY alertDate DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.stockmaster.models.StockAlert>> getAllAlerts();
    
    @androidx.room.Query(value = "SELECT * FROM stock_alerts ORDER BY alertDate DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllAlertsList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.stockmaster.models.StockAlert>> $completion);
    
    @androidx.room.Query(value = "UPDATE stock_alerts SET isRead = 1 WHERE id = :alertId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAsRead(int alertId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM stock_alerts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM stock_alerts WHERE isRead = 0")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getUnreadCount();
}