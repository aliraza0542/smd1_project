package com.stockmaster.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stockmaster.R
import com.stockmaster.models.StockAlert
import com.stockmaster.utils.DateUtils

class AlertAdapter(
    private val context: Context,
    private val onAlertClick: (StockAlert, Int) -> Unit
) : RecyclerView.Adapter<AlertAdapter.AlertViewHolder>() {

    private val alertList = mutableListOf<StockAlert>()

    inner class AlertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.iv_alert_icon)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_alert_title)
        val tvSubtitle: TextView = itemView.findViewById(R.id.tv_alert_subtitle)
        val tvDate: TextView = itemView.findViewById(R.id.tv_alert_date)
        val tvStockLevel: TextView = itemView.findViewById(R.id.tv_alert_stock_level)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stock_alert, parent, false)
        return AlertViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        val alert = alertList[position]

        holder.tvTitle.text = alert.productName
        holder.tvSubtitle.text = "Stock: ${alert.currentStock} / Threshold: ${alert.threshold}"
        holder.tvDate.text = DateUtils.formatDate(alert.alertDate)
        holder.tvStockLevel.text = "${alert.currentStock} left"

        if (alert.currentStock == 0) {
            holder.tvStockLevel.setBackgroundResource(R.drawable.bg_badge_error)
            holder.tvStockLevel.setTextColor(ContextCompat.getColor(context, R.color.on_error_container))
        } else {
            holder.tvStockLevel.setBackgroundResource(R.drawable.bg_badge_warning)
            holder.tvStockLevel.setTextColor(ContextCompat.getColor(context, R.color.on_tertiary_fixed))
        }

        // Gray out read alerts
        val alpha = if (alert.isRead) 0.5f else 1.0f
        holder.itemView.alpha = alpha

        holder.itemView.setOnClickListener {
            onAlertClick(alert, position)
        }
    }

    override fun getItemCount(): Int = alertList.size

    fun setData(list: List<StockAlert>?) {
        alertList.clear()
        alertList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }

    fun markRead(position: Int) {
        if (position in alertList.indices) {
            alertList[position].isRead = true
            notifyItemChanged(position)
        }
    }

    fun getAlerts(): List<StockAlert> = alertList.toList()
}
