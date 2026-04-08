package com.stockmaster.activities

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.stockmaster.R
import com.stockmaster.models.Sale
import com.stockmaster.utils.DateUtils
import com.stockmaster.utils.ProfitCalculator

class SaleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale_detail)

        // F2 — Retrieve Serializable Sale from Bundle
        val b = intent.extras ?: run { finish(); return }
        val sale = b.getSerializable("SALE_OBJECT") as? Sale
            ?: run { finish(); return }

        val btnBack = findViewById<ImageButton>(R.id.btn_back_sale)
        val tvSaleId = findViewById<TextView>(R.id.tv_sale_id)
        val tvProductName = findViewById<TextView>(R.id.tv_sale_product_name)
        val tvSaleDate = findViewById<TextView>(R.id.tv_sale_date)
        val tvQuantity = findViewById<TextView>(R.id.tv_sale_quantity)
        val tvPurchasePrice = findViewById<TextView>(R.id.tv_sale_purchase_price)
        val tvSellingPrice = findViewById<TextView>(R.id.tv_sale_selling_price)
        val tvTotalRevenue = findViewById<TextView>(R.id.tv_sale_total_revenue)
        val tvTotalProfit = findViewById<TextView>(R.id.tv_sale_total_profit)
        val tvMarginBadge = findViewById<TextView>(R.id.tv_sale_margin_badge)

        btnBack.setOnClickListener { finish() }

        val trxId = String.format("TRX-%04d", sale.id)
        tvSaleId.text = "Sale #$trxId"
        tvProductName.text = sale.productName
        tvSaleDate.text = DateUtils.formatDateTime(sale.saleDate)
        tvQuantity.text = "${sale.quantitySold} units"
        tvPurchasePrice.text = "Rs. ${String.format("%,.0f", sale.purchasePrice)}"
        tvSellingPrice.text = "Rs. ${String.format("%,.0f", sale.sellingPrice)}"
        tvTotalRevenue.text = "Rs. ${String.format("%,.0f", sale.totalRevenue)}"
        tvTotalProfit.text = "Rs. ${String.format("%,.0f", sale.totalProfit)}"

        val marginLabel = ProfitCalculator.getMarginLabel(sale.profitMarginPercent)
        tvMarginBadge.text = "$marginLabel ${String.format("%.1f", sale.profitMarginPercent)}%"
        val marginColorRes = ProfitCalculator.getMarginColorRes(sale.profitMarginPercent)
        tvMarginBadge.setTextColor(ContextCompat.getColor(this, marginColorRes))

        when {
            sale.profitMarginPercent > 20 -> {
                tvMarginBadge.setBackgroundResource(R.drawable.bg_badge_success)
            }
            sale.profitMarginPercent > 10 -> {
                tvMarginBadge.setBackgroundResource(R.drawable.bg_badge_warning)
            }
            else -> {
                tvMarginBadge.setBackgroundResource(R.drawable.bg_badge_error)
            }
        }
    }
}
