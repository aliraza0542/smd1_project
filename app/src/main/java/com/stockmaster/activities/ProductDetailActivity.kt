package com.stockmaster.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stockmaster.R
import com.stockmaster.models.Product
import com.stockmaster.utils.ProfitCalculator
import com.stockmaster.viewmodels.ProductViewModel

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel

    private lateinit var tvDetailName: TextView
    private lateinit var tvCategoryBadge: TextView
    private lateinit var tvPurchasePrice: TextView
    private lateinit var tvSellingPrice: TextView
    private lateinit var tvMarginBadge: TextView
    private lateinit var tvCurrentStock: TextView
    private lateinit var tvStockStatus: TextView
    private lateinit var tvThreshold: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnEdit: ImageButton
    private lateinit var btnBack: ImageButton
    private lateinit var btnDelete: MaterialButton

    private var currentProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        // F2 — Retrieve Serializable Product from Bundle
        val b = intent.extras ?: run { finish(); return }
        val product = b.getSerializable("PRODUCT_OBJECT") as? Product
            ?: run { finish(); return }
        currentProduct = product

        // Bind views
        tvDetailName = findViewById(R.id.tv_detail_name)
        tvCategoryBadge = findViewById(R.id.tv_detail_category)
        tvPurchasePrice = findViewById(R.id.tv_purchase_price)
        tvSellingPrice = findViewById(R.id.tv_selling_price)
        tvMarginBadge = findViewById(R.id.tv_margin_badge)
        tvCurrentStock = findViewById(R.id.tv_current_stock)
        tvStockStatus = findViewById(R.id.tv_stock_status)
        tvThreshold = findViewById(R.id.tv_threshold)
        tvDescription = findViewById(R.id.tv_description)
        btnEdit = findViewById(R.id.btn_edit)
        btnBack = findViewById(R.id.btn_back)
        btnDelete = findViewById(R.id.btn_delete)

        bindProduct(product)

        // Admin-only controls
        val userRole = intent.getStringExtra("USER_ROLE") ?: "STAFF"
        if (userRole != "ADMIN") {
            btnEdit.visibility = View.GONE
            btnDelete.visibility = View.GONE
        }

        btnBack.setOnClickListener { finish() }

        btnEdit.setOnClickListener {
            val editIntent = Intent(this, AddEditProductActivity::class.java)
            val editBundle = Bundle()
            editBundle.putSerializable("PRODUCT_OBJECT", product)
            editBundle.putString("MODE", "EDIT")
            editIntent.putExtras(editBundle)
            startActivity(editIntent)
            finish()
        }

        btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Delete Product")
                .setMessage("Are you sure you want to delete \"${product.name}\"? This action cannot be undone.")
                .setPositiveButton("Delete") { _, _ ->
                    productViewModel.delete(product) {
                        finish()
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun bindProduct(product: Product) {
        tvDetailName.text = product.name
        tvCategoryBadge.text = product.category
        tvPurchasePrice.text = "Rs. ${String.format("%,.0f", product.purchasePrice)}"
        tvSellingPrice.text = "Rs. ${String.format("%,.0f", product.sellingPrice)}"
        tvCurrentStock.text = "${product.stockQuantity}"
        tvThreshold.text = "Low Stock Threshold: ${product.lowStockThreshold} units"
        tvDescription.text = if (product.description.isNotEmpty()) product.description else "No description available."

        // Margin badge
        val margin = ProfitCalculator.calculateMarginPercent(product.purchasePrice, product.sellingPrice)
        val marginLabel = ProfitCalculator.getMarginLabel(margin)
        tvMarginBadge.text = "$marginLabel ${String.format("%.1f", margin)}%"
        val marginColorRes = ProfitCalculator.getMarginColorRes(margin)
        tvMarginBadge.setTextColor(ContextCompat.getColor(this, marginColorRes))

        // Stock status badge
        when {
            product.stockQuantity == 0 -> {
                tvStockStatus.text = "OUT OF STOCK"
                tvStockStatus.setBackgroundResource(R.drawable.bg_badge_error)
                tvStockStatus.setTextColor(ContextCompat.getColor(this, R.color.on_error_container))
            }
            product.stockQuantity <= product.lowStockThreshold -> {
                tvStockStatus.text = "LOW STOCK"
                tvStockStatus.setBackgroundResource(R.drawable.bg_badge_warning)
                tvStockStatus.setTextColor(ContextCompat.getColor(this, R.color.on_tertiary_fixed))
            }
            else -> {
                tvStockStatus.text = "IN STOCK"
                tvStockStatus.setBackgroundResource(R.drawable.bg_badge_success)
                tvStockStatus.setTextColor(ContextCompat.getColor(this, R.color.on_secondary_fixed))
            }
        }
    }
}
