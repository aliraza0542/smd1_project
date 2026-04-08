package com.stockmaster.utils;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0005J\u0016\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005\u00a8\u0006\u0010"}, d2 = {"Lcom/stockmaster/utils/ProfitCalculator;", "", "<init>", "()V", "calculateProfit", "", "purchase", "selling", "qty", "", "calculateMarginPercent", "getMarginLabel", "", "margin", "getMarginColorRes", "calculateMarginFromPrices", "app_debug"})
public final class ProfitCalculator {
    @org.jetbrains.annotations.NotNull()
    public static final com.stockmaster.utils.ProfitCalculator INSTANCE = null;
    
    private ProfitCalculator() {
        super();
    }
    
    public final double calculateProfit(double purchase, double selling, int qty) {
        return 0.0;
    }
    
    public final double calculateMarginPercent(double purchase, double selling) {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMarginLabel(double margin) {
        return null;
    }
    
    public final int getMarginColorRes(double margin) {
        return 0;
    }
    
    public final double calculateMarginFromPrices(double purchase, double selling) {
        return 0.0;
    }
}