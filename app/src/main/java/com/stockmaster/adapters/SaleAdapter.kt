package com.stockmaster.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stockmaster.R
import com.stockmaster.activities.SaleDetailActivity
import com.stockmaster.models.Sale
import com.stockmaster.utils.DateUtils
import com.stockmaster.utils.ProfitCalculator

class SaleAdapter(
    private val context: Context
) : RecyclerView.Adapter<SaleAdapter.SaleViewHolder>() {

    private val saleList = mutableListOf<Sale>()

    inner class SaleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.iv_sale_icon)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_sale_title)
        val tvSubtitle: TextView = itemView.findViewById(R.id.tv_sale_subtitle)
        val tvAmount: TextView = itemView.findViewById(R.id.tv_sale_amount)
        val tvMarginBadge: TextView = itemView.findViewById(R.id.tv_margin_badge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sale, parent, false)
        return SaleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
        val sale = saleList[position]

        val trxId = String.format("TRX-%04d", sale.id)
        holder.tvTitle.text = "Sale #$trxId"
        holder.tvSubtitle.text = "${DateUtils.formatDate(sale.saleDate)} • ${sale.productName}"
        holder.tvAmount.text = "+Rs.${String.format("%,.0f", sale.totalRevenue)}"

        // Margin badge
        val marginLabel = ProfitCalculator.getMarginLabel(sale.profitMarginPercent)
        holder.tvMarginBadge.text = "$marginLabel ${String.format("%.0f", sale.profitMarginPercent)}%"
        val marginColorRes = ProfitCalculator.getMarginColorRes(sale.profitMarginPercent)
        holder.tvMarginBadge.setTextColor(ContextCompat.getColor(context, marginColorRes))

        when {
            sale.profitMarginPercent > 20 -> {
                holder.tvMarginBadge.setBackgroundResource(R.drawable.bg_badge_success)
            }
            sale.profitMarginPercent > 10 -> {
                holder.tvMarginBadge.setBackgroundResource(R.drawable.bg_badge_warning)
            }
            else -> {
                holder.tvMarginBadge.setBackgroundResource(R.drawable.bg_badge_error)
            }
        }

        // Click opens SaleDetailActivity with Serializable Bundle
        holder.itemView.setOnClickListener {
            val intent = Intent(context, SaleDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("SALE_OBJECT", sale)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = saleList.size

    fun setData(list: List<Sale>?) {
        saleList.clear()
        saleList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }
}
