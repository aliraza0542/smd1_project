package com.stockmaster.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stockmaster.R
import com.stockmaster.models.Product

class StockAdapter(
    private val context: Context
) : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    private val stockList = mutableListOf<Product>()

    inner class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        val tvCategory: TextView = itemView.findViewById(R.id.tv_category_badge)
        val tvStockQty: TextView = itemView.findViewById(R.id.tv_stock_qty)
        val badgeStatus: TextView = itemView.findViewById(R.id.badge_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val product = stockList[position]

        holder.tvProductName.text = product.name
        holder.tvCategory.text = product.category
        holder.tvStockQty.text = "${product.stockQuantity} units"

        // Reuse item_product layout but focus on stock info
        val tvPurchasePrice = holder.itemView.findViewById<TextView>(R.id.tv_purchase_price)
        val tvSellingPrice = holder.itemView.findViewById<TextView>(R.id.tv_selling_price)
        tvPurchasePrice.text = "Purchase: Rs.${String.format("%,.0f", product.purchasePrice)}"
        tvSellingPrice.text = "Selling: Rs.${String.format("%,.0f", product.sellingPrice)}"

        when {
            product.stockQuantity == 0 -> {
                holder.badgeStatus.text = "OUT OF STOCK"
                holder.badgeStatus.setBackgroundResource(R.drawable.bg_badge_error)
                holder.badgeStatus.setTextColor(ContextCompat.getColor(context, R.color.on_error_container))
            }
            product.stockQuantity <= product.lowStockThreshold -> {
                holder.badgeStatus.text = "LOW STOCK"
                holder.badgeStatus.setBackgroundResource(R.drawable.bg_badge_warning)
                holder.badgeStatus.setTextColor(ContextCompat.getColor(context, R.color.on_tertiary_fixed))
            }
            else -> {
                holder.badgeStatus.text = "IN STOCK"
                holder.badgeStatus.setBackgroundResource(R.drawable.bg_badge_success)
                holder.badgeStatus.setTextColor(ContextCompat.getColor(context, R.color.on_secondary_fixed))
            }
        }
    }

    override fun getItemCount(): Int = stockList.size

    fun setData(list: List<Product>?) {
        stockList.clear()
        stockList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }
}
