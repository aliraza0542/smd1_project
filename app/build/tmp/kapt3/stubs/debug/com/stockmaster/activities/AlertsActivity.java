package com.stockmaster.activities;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\u0018\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/stockmaster/activities/AlertsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "viewModel", "Lcom/stockmaster/viewmodels/AlertsViewModel;", "alertAdapter", "Lcom/stockmaster/adapters/AlertAdapter;", "rvAlerts", "Landroidx/recyclerview/widget/RecyclerView;", "layoutEmptyAlerts", "Landroid/widget/LinearLayout;", "btnClearAll", "Lcom/google/android/material/button/MaterialButton;", "userRole", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "loadAlerts", "markAlertAsRead", "alert", "Lcom/stockmaster/models/StockAlert;", "position", "", "clearAllAlerts", "app_debug"})
public final class AlertsActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.stockmaster.viewmodels.AlertsViewModel viewModel;
    private com.stockmaster.adapters.AlertAdapter alertAdapter;
    private androidx.recyclerview.widget.RecyclerView rvAlerts;
    private android.widget.LinearLayout layoutEmptyAlerts;
    private com.google.android.material.button.MaterialButton btnClearAll;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String userRole = "STAFF";
    
    public AlertsActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadAlerts() {
    }
    
    private final void markAlertAsRead(com.stockmaster.models.StockAlert alert, int position) {
    }
    
    private final void clearAllAlerts() {
    }
}