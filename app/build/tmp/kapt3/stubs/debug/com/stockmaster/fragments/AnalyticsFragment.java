package com.stockmaster.fragments;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J&\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u001b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\"\u001a\u00020\u0017H\u0002J\u000e\u0010#\u001a\u00020\u0013H\u0082@\u00a2\u0006\u0002\u0010$J\u000e\u0010%\u001a\u00020\u0013H\u0082@\u00a2\u0006\u0002\u0010$J\u000e\u0010&\u001a\u00020\u0013H\u0082@\u00a2\u0006\u0002\u0010$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/stockmaster/fragments/AnalyticsFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "saleViewModel", "Lcom/stockmaster/viewmodels/SaleViewModel;", "btnFromDate", "Lcom/google/android/material/button/MaterialButton;", "btnToDate", "chipGroupReportType", "Lcom/google/android/material/chip/ChipGroup;", "btnGenerate", "tvReportOutput", "Landroid/widget/TextView;", "btnShare", "fromDate", "", "toDate", "reportType", "", "reportText", "userRole", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "generateReport", "generateSalesSummary", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateProfitReport", "generateStockReport", "app_debug"})
public final class AnalyticsFragment extends androidx.fragment.app.Fragment {
    private com.stockmaster.viewmodels.SaleViewModel saleViewModel;
    private com.google.android.material.button.MaterialButton btnFromDate;
    private com.google.android.material.button.MaterialButton btnToDate;
    private com.google.android.material.chip.ChipGroup chipGroupReportType;
    private com.google.android.material.button.MaterialButton btnGenerate;
    private android.widget.TextView tvReportOutput;
    private com.google.android.material.button.MaterialButton btnShare;
    private long fromDate;
    private long toDate;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String reportType = "Sales Summary";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String reportText = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String userRole = "STAFF";
    
    public AnalyticsFragment() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void generateReport() {
    }
    
    private final java.lang.Object generateSalesSummary(kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.Object generateProfitReport(kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.Object generateStockReport(kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
}