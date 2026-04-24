package com.stockmaster.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.stockmaster.R

// F4 — Fragment Transaction target for bottom nav
class POSFragment : Fragment() {

    private var item1Qty = 1
    private var item2Qty = 2
    private val item1Price = 249.00
    private val item2Price = 399.00
    private val taxRate = 0.08
    private val discount = 47.00

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupQuantityControls(view)
        setupActionButtons(view)
        updateTotals(view)
    }

    private fun setupQuantityControls(view: View) {
        val tvQty1 = view.findViewById<TextView>(R.id.tv_qty_1)
        val tvQty2 = view.findViewById<TextView>(R.id.tv_qty_2)
        val btnMinus1 = view.findViewById<Button>(R.id.btn_minus_1)
        val btnPlus1 = view.findViewById<Button>(R.id.btn_plus_1)
        val btnMinus2 = view.findViewById<Button>(R.id.btn_minus_2)
        val btnPlus2 = view.findViewById<Button>(R.id.btn_plus_2)

        tvQty1?.text = item1Qty.toString()
        tvQty2?.text = item2Qty.toString()

        btnMinus1?.setOnClickListener {
            if (item1Qty > 0) { item1Qty--; tvQty1?.text = item1Qty.toString(); updateTotals(view) }
        }
        btnPlus1?.setOnClickListener {
            item1Qty++; tvQty1?.text = item1Qty.toString(); updateTotals(view)
        }
        btnMinus2?.setOnClickListener {
            if (item2Qty > 0) { item2Qty--; tvQty2?.text = item2Qty.toString(); updateTotals(view) }
        }
        btnPlus2?.setOnClickListener {
            item2Qty++; tvQty2?.text = item2Qty.toString(); updateTotals(view)
        }
    }

    private fun updateTotals(view: View) {
        val subtotal = (item1Qty * item1Price) + (item2Qty * item2Price)
        val tax = subtotal * taxRate
        val total = subtotal + tax - discount

        view.findViewById<TextView>(R.id.tv_subtotal)?.text = "$${String.format("%.2f", subtotal)}"
        view.findViewById<TextView>(R.id.tv_tax)?.text = "$${String.format("%.2f", tax)}"
        view.findViewById<TextView>(R.id.tv_discount)?.text = "- $${String.format("%.2f", discount)}"
        view.findViewById<TextView>(R.id.tv_total)?.text = "$${String.format("%.2f", total)}"
    }

    private fun setupActionButtons(view: View) {
        view.findViewById<Button>(R.id.btn_draft)?.setOnClickListener {
            Toast.makeText(requireContext(), "Order saved as draft", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.btn_pay)?.setOnClickListener {
            Toast.makeText(requireContext(), "Processing payment...", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.btn_complete_sale)?.setOnClickListener {
            Toast.makeText(requireContext(), "Sale completed!", Toast.LENGTH_LONG).show()
        }
    }
}
