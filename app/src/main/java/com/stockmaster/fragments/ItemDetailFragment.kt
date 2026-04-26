package com.stockmaster.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.stockmaster.R
import com.stockmaster.database.DatabaseHelper
import com.stockmaster.database.dao.ProductDao
import com.stockmaster.database.dao.SaleDao
import com.stockmaster.models.Product
import com.stockmaster.network.ApiClient
import com.stockmaster.repository.ProductRepository
import kotlinx.coroutines.launch

// F2 — Bundle: receives product data (name, price, stock, category) from InventoryFragment
// F4 — Fragment Transaction target (added to back stack)
class ItemDetailFragment : Fragment() {

    private lateinit var repository: ProductRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHelper = DatabaseHelper.getInstance(requireContext())
        repository = ProductRepository(
            productDao = ProductDao(dbHelper),
            saleDao = SaleDao(dbHelper),
            apiService = ApiClient.apiService
        )

        // F2 — Extract product data from Bundle arguments
        val product: Product? = arguments?.getParcelable("product")

        product?.let { p ->
            view.findViewById<TextView>(R.id.tv_detail_name)?.text = p.name
            view.findViewById<TextView>(R.id.tv_detail_sku)?.text = p.sku
            view.findViewById<TextView>(R.id.tv_detail_category)?.text = p.category
            view.findViewById<TextView>(R.id.tv_detail_price)?.text = "$${String.format("%.2f", p.price)}"
            view.findViewById<TextView>(R.id.tv_detail_stock)?.text = "${p.stock} units"
            view.findViewById<TextView>(R.id.tv_detail_description)?.text = p.description

            val tvStatus = view.findViewById<TextView>(R.id.tv_detail_status)
            when (p.status) {
                "critical" -> {
                    tvStatus?.text = "CRITICAL STOCK"
                    tvStatus?.setTextColor(Color.parseColor("#FF5252"))
                }
                "low", "low_stock" -> {
                    tvStatus?.text = "LOW STOCK"
                    tvStatus?.setTextColor(Color.parseColor("#FF7043"))
                }
                else -> {
                    tvStatus?.text = "IN STOCK"
                    tvStatus?.setTextColor(Color.parseColor("#00BFA5"))
                }
            }

            view.findViewById<Button>(R.id.btn_record_sale)?.setOnClickListener {
                showRecordSaleDialog(p)
            }
        }

        view.findViewById<Button>(R.id.btn_back)?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun showRecordSaleDialog(product: Product) {
        val input = EditText(requireContext()).apply {
            hint = getString(R.string.record_sale_quantity_hint)
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
        }

        AlertDialog.Builder(requireContext())
            .setTitle(R.string.record_sale)
            .setView(input)
            .setNegativeButton(android.R.string.cancel, null)
            .setPositiveButton(R.string.record_sale) { _, _ ->
                val qty = input.text.toString().toIntOrNull()
                if (qty == null || qty <= 0) {
                    Toast.makeText(requireContext(), R.string.record_sale_validation, Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                lifecycleScope.launch {
                    val freshProduct = repository.getProductById(product.id) ?: product
                    val success = repository.recordSale(freshProduct, qty)
                    if (success) {
                        Toast.makeText(requireContext(), R.string.record_sale_success, Toast.LENGTH_SHORT).show()
                        parentFragmentManager.setFragmentResult(
                            AddEditProductFragment.REQUEST_KEY,
                            Bundle().apply { putBoolean(AddEditProductFragment.BUNDLE_REFRESH, true) }
                        )
                        parentFragmentManager.popBackStack()
                    } else {
                        Toast.makeText(requireContext(), R.string.record_sale_failed, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .show()
    }
}
