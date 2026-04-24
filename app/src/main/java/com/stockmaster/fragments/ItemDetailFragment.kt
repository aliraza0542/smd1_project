package com.stockmaster.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.stockmaster.R
import com.stockmaster.models.Product

// F2 — Bundle: receives product data (name, price, stock, category) from InventoryFragment
// F4 — Fragment Transaction target (added to back stack)
class ItemDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // F2 — Extract product data from Bundle arguments
        val product: Product? = arguments?.getParcelable("product")

        product?.let { p ->
            view.findViewById<TextView>(R.id.tv_detail_name)?.text = p.name
            view.findViewById<TextView>(R.id.tv_detail_category)?.text = p.category
            view.findViewById<TextView>(R.id.tv_detail_price)?.text = "$${String.format("%.2f", p.price)}"
            view.findViewById<TextView>(R.id.tv_detail_stock)?.text = "${p.stock} units"

            val tvStatus = view.findViewById<TextView>(R.id.tv_detail_status)
            when (p.status) {
                "critical" -> {
                    tvStatus?.text = "CRITICAL STOCK"
                    tvStatus?.setTextColor(Color.parseColor("#FF5252"))
                }
                "low" -> {
                    tvStatus?.text = "LOW STOCK"
                    tvStatus?.setTextColor(Color.parseColor("#FF7043"))
                }
                else -> {
                    tvStatus?.text = "IN STOCK"
                    tvStatus?.setTextColor(Color.parseColor("#00BFA5"))
                }
            }
        }

        view.findViewById<Button>(R.id.btn_back)?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}
