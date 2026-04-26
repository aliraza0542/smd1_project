package com.stockmaster.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stockmaster.R
import com.stockmaster.models.Product

// F3 — RecyclerView with custom adapter (InventoryAdapter) and ViewHolder (InventoryViewHolder)
class InventoryAdapter(
    private var products: List<Product>,
    private val onItemClick: (Product) -> Unit,
    private val onItemLongClick: (Product) -> Unit
) : RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>() {

    // F3 — Custom ViewHolder
    inner class InventoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_product_name)
        val tvCategory: TextView = itemView.findViewById(R.id.tv_product_category)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_product_price)
        val tvBadge: TextView = itemView.findViewById(R.id.tv_stock_badge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        // F3 — Custom row layout: item_inventory_card.xml
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inventory_card, parent, false)
        return InventoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val product = products[position]

        holder.tvName.text = product.name
        holder.tvCategory.text = "${product.category} • ${product.sku}"
        holder.tvPrice.text = "$${String.format("%.2f", product.price)}"
        holder.itemView.setBackgroundResource(
            if (position % 2 == 0) R.drawable.bg_surface_low else R.drawable.bg_surface_lowest
        )

        when (product.status) {
            "critical" -> {
                holder.tvBadge.text = "CRITICAL: ${product.stock} LEFT"
                holder.tvBadge.setBackgroundResource(R.drawable.bg_badge_critical)
                holder.tvBadge.setTextColor(Color.parseColor("#FF5252"))
            }
            "low", "low_stock" -> {
                holder.tvBadge.text = "LOW: ${product.stock} LEFT"
                holder.tvBadge.setBackgroundResource(R.drawable.bg_badge_low)
                holder.tvBadge.setTextColor(Color.parseColor("#FF7043"))
            }
            else -> {
                holder.tvBadge.text = "STOCK: ${product.stock}"
                holder.tvBadge.setBackgroundResource(R.drawable.bg_badge_ok)
                holder.tvBadge.setTextColor(Color.parseColor("#00BFA5"))
            }
        }

        // F2 — Clicking an item opens ItemDetailFragment with product data via Bundle
        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClick(product)
            true
        }
    }

    override fun getItemCount(): Int = products.size

    // F5 — Search/Filter: update list and notify adapter
    fun updateList(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Product = products[position]

    fun getItems(): List<Product> = products
}
