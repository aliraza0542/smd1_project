package com.stockmaster.activities;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\u0018\u0010&\u001a\u00020#2\u0006\u0010\'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u0011H\u0002J\b\u0010)\u001a\u00020#H\u0002J\b\u0010*\u001a\u00020#H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/stockmaster/activities/AddEditProductActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "productViewModel", "Lcom/stockmaster/viewmodels/ProductViewModel;", "tvTitle", "Landroid/widget/TextView;", "tilName", "Lcom/google/android/material/textfield/TextInputLayout;", "tilCategory", "tilDescription", "tilPurchase", "tilSelling", "tilStock", "tilThreshold", "etName", "Lcom/google/android/material/textfield/TextInputEditText;", "actCategory", "Landroid/widget/AutoCompleteTextView;", "etDescription", "etPurchase", "etSelling", "etStock", "etThreshold", "tvMarginPreview", "btnSave", "Lcom/google/android/material/button/MaterialButton;", "btnBack", "Lcom/google/android/material/appbar/MaterialToolbar;", "editProduct", "Lcom/stockmaster/models/Product;", "isEditMode", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupErrorClearing", "til", "et", "updateMarginPreview", "saveProduct", "app_debug"})
public final class AddEditProductActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.stockmaster.viewmodels.ProductViewModel productViewModel;
    private android.widget.TextView tvTitle;
    private com.google.android.material.textfield.TextInputLayout tilName;
    private com.google.android.material.textfield.TextInputLayout tilCategory;
    private com.google.android.material.textfield.TextInputLayout tilDescription;
    private com.google.android.material.textfield.TextInputLayout tilPurchase;
    private com.google.android.material.textfield.TextInputLayout tilSelling;
    private com.google.android.material.textfield.TextInputLayout tilStock;
    private com.google.android.material.textfield.TextInputLayout tilThreshold;
    private com.google.android.material.textfield.TextInputEditText etName;
    private android.widget.AutoCompleteTextView actCategory;
    private com.google.android.material.textfield.TextInputEditText etDescription;
    private com.google.android.material.textfield.TextInputEditText etPurchase;
    private com.google.android.material.textfield.TextInputEditText etSelling;
    private com.google.android.material.textfield.TextInputEditText etStock;
    private com.google.android.material.textfield.TextInputEditText etThreshold;
    private android.widget.TextView tvMarginPreview;
    private com.google.android.material.button.MaterialButton btnSave;
    private com.google.android.material.appbar.MaterialToolbar btnBack;
    @org.jetbrains.annotations.Nullable()
    private com.stockmaster.models.Product editProduct;
    private boolean isEditMode = false;
    
    public AddEditProductActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupErrorClearing(com.google.android.material.textfield.TextInputLayout til, com.google.android.material.textfield.TextInputEditText et) {
    }
    
    private final void updateMarginPreview() {
    }
    
    private final void saveProduct() {
    }
}