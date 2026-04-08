package com.stockmaster.activities;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0016H\u0014J\u0018\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005H\u0002J\u000e\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0014J\b\u0010 \u001a\u00020\u0016H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/stockmaster/activities/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "userName", "", "getUserName", "()Ljava/lang/String;", "userName$delegate", "Lkotlin/Lazy;", "userRole", "getUserRole", "userRole$delegate", "bottomNav", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "fab", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "btnNotifications", "Landroid/widget/ImageButton;", "currentTabId", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "loadFragment", "fragment", "Landroidx/fragment/app/Fragment;", "tag", "navigateToTab", "tabId", "updateAlertBadge", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy userName$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy userRole$delegate = null;
    private com.google.android.material.bottomnavigation.BottomNavigationView bottomNav;
    private com.google.android.material.floatingactionbutton.FloatingActionButton fab;
    private android.widget.ImageButton btnNotifications;
    private int currentTabId = com.stockmaster.R.id.nav_dashboard;
    
    public MainActivity() {
        super();
    }
    
    private final java.lang.String getUserName() {
        return null;
    }
    
    private final java.lang.String getUserRole() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void loadFragment(androidx.fragment.app.Fragment fragment, java.lang.String tag) {
    }
    
    public final void navigateToTab(int tabId) {
    }
    
    private final void updateAlertBadge() {
    }
}