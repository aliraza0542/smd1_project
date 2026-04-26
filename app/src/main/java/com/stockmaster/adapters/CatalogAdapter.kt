package com.stockmaster.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stockmaster.R
import com.stockmaster.models.CatalogProduct

class CatalogAdapter(
    private var products: List<CatalogProduct>,
    private val onImportClick: (CatalogProduct) -> Unit
) : RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    inner class CatalogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.iv_catalog_thumb)
        val name: TextView = itemView.findViewById(R.id.tv_catalog_name)
        val category: TextView = itemView.findViewById(R.id.tv_catalog_category)
        val price: TextView = itemView.findViewById(R.id.tv_catalog_price)
        val stockBadge: TextView = itemView.findViewById(R.id.tv_catalog_stock)
        val importBtn: Button = itemView.findViewById(R.id.btn_import)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_catalog_card, parent, false)
        return CatalogViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val product = products[position]
        holder.name.text = product.title
        holder.category.text = product.category
        holder.price.text = "$${String.format("%.2f", product.price)}"

        val status = when {
            product.stock < 5 -> "CRITICAL"
            product.stock < 10 -> "LOW"
            else -> "IN STOCK"
        }
        holder.stockBadge.text = "$status • ${product.stock}"

        Glide.with(holder.itemView.context)
            .load(product.thumbnail)
            .placeholder(R.drawable.bg_product_placeholder)
            .error(R.drawable.bg_product_placeholder)
            .into(holder.image)

        holder.importBtn.setOnClickListener { onImportClick(product) }
    }

    override fun getItemCount(): Int = products.size

    fun updateList(newProducts: List<CatalogProduct>) {
        products = newProducts
        notifyDataSetChanged()
    }
}

