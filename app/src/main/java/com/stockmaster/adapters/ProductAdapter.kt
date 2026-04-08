package com.stockmaster.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stockmaster.R
import com.stockmaster.activities.ProductDetailActivity
import com.stockmaster.models.Product

class ProductAdapter(
    private val context: Context
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val fullList = mutableListOf<Product>()
    private val filteredList = mutableListOf<Product>()
    private var currentQuery = ""
    private var currentCategory = "All"

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        val tvCategory: TextView = itemView.findViewById(R.id.tv_category_badge)
        val tvPurchasePrice: TextView = itemView.findViewById(R.id.tv_purchase_price)
        val tvSellingPrice: TextView = itemView.findViewById(R.id.tv_selling_price)
        val tvStockQty: TextView = itemView.findViewById(R.id.tv_stock_qty)
        val badgeStatus: TextView = itemView.findViewById(R.id.badge_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = filteredList[position]

        holder.tvProductName.text = product.name
        holder.tvCategory.text = product.category
        holder.tvPurchasePrice.text = "Purchase: Rs.${String.format("%,.0f", product.purchasePrice)}"
        holder.tvSellingPrice.text = "Selling: Rs.${String.format("%,.0f", product.sellingPrice)}"
        holder.tvStockQty.text = "${product.stockQuantity} units"

        // Status badge
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

        // F2 — Click opens ProductDetailActivity with Serializable Bundle
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("PRODUCT_OBJECT", product)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = filteredList.size

    fun setData(list: List<Product>?) {
        fullList.clear()
        fullList.addAll(list ?: emptyList())
        filter(currentQuery, currentCategory)
    }

    // F5 — Search and filter simultaneously
    fun filter(query: String, category: String = "All") {
        currentQuery = query
        currentCategory = category
        filteredList.clear()
        val q = query.lowercase().trim()
        fullList.forEach { p ->
            val textMatch = q.isEmpty() || p.name.lowercase().contains(q)
                    || p.category.lowercase().contains(q)
            val catMatch = category == "All" || p.category == category
            if (textMatch && catMatch) filteredList.add(p)
        }
        notifyDataSetChanged()
    }

    fun getFilteredList(): List<Product> = filteredList.toList()
}
