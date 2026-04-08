package com.stockmaster.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.stockmaster.R
import com.stockmaster.adapters.SaleAdapter
import com.stockmaster.models.Product
import com.stockmaster.models.Sale
import com.stockmaster.models.StockAlert
import com.stockmaster.utils.ProfitCalculator
import com.stockmaster.viewmodels.ProductViewModel
import com.stockmaster.viewmodels.SaleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat

class SalesFragment : Fragment() {

    private lateinit var saleViewModel: SaleViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var saleAdapter: SaleAdapter

    private lateinit var tvTodaySales: TextView
    private lateinit var tvTodayProfit: TextView
    private lateinit var tvTransactions: TextView
    private lateinit var rvSales: RecyclerView
    private lateinit var layoutEmptySales: LinearLayout

    private var productList: List<Product> = emptyList()
    private var userRole: String = "STAFF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userRole = arguments?.getString("USER_ROLE") ?: "STAFF"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sales, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saleViewModel = ViewModelProvider(this)[SaleViewModel::class.java]
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        tvTodaySales = view.findViewById(R.id.tv_today_sales_value)
        tvTodayProfit = view.findViewById(R.id.tv_today_profit_value)
        tvTransactions = view.findViewById(R.id.tv_transactions_value)
        rvSales = view.findViewById(R.id.rv_sales)
        layoutEmptySales = view.findViewById(R.id.layout_empty_sales)

        // Setup RecyclerView
        saleAdapter = SaleAdapter(requireContext())
        rvSales.layoutManager = LinearLayoutManager(requireContext())
        rvSales.adapter = saleAdapter

        // Observe data
        saleViewModel.allSales.observe(viewLifecycleOwner) { sales ->
            saleAdapter.setData(sales)
            if (sales.isNullOrEmpty()) {
                rvSales.visibility = View.GONE
                layoutEmptySales.visibility = View.VISIBLE
            } else {
                rvSales.visibility = View.VISIBLE
                layoutEmptySales.visibility = View.GONE
            }
        }

        saleViewModel.todayRevenue.observe(viewLifecycleOwner) { revenue ->
            tvTodaySales.text = "Rs. ${String.format("%,.0f", revenue ?: 0.0)}"
        }

        saleViewModel.todayProfit.observe(viewLifecycleOwner) { profit ->
            tvTodayProfit.text = "Rs. ${String.format("%,.0f", profit ?: 0.0)}"
        }

        saleViewModel.todayTransactionCount.observe(viewLifecycleOwner) { count ->
            tvTransactions.text = "${count ?: 0}"
        }

        // Cache product list for bottom sheet
        productViewModel.allProducts.observe(viewLifecycleOwner) { products ->
            productList = products ?: emptyList()
        }
    }

    fun showAddSaleBottomSheet() {
        if (!isAdded) return
        val dialog = BottomSheetDialog(requireContext(), R.style.Theme_StockMaster_BottomSheet)
        val sheetView = layoutInflater.inflate(R.layout.dialog_add_sale, null)
        dialog.setContentView(sheetView)

        val actProduct = sheetView.findViewById<AutoCompleteTextView>(R.id.act_product_select)
        val tilQuantity = sheetView.findViewById<TextInputLayout>(R.id.til_quantity)
        val etQuantity = sheetView.findViewById<TextInputEditText>(R.id.et_quantity)
        val tvUnitProfit = sheetView.findViewById<TextView>(R.id.tv_unit_profit)
        val tvTotalRevenue = sheetView.findViewById<TextView>(R.id.tv_total_revenue_preview)
        val tvTotalProfit = sheetView.findViewById<TextView>(R.id.tv_total_profit_preview)
        val tvMarginLabel = sheetView.findViewById<TextView>(R.id.tv_margin_label)
        val btnConfirm = sheetView.findViewById<MaterialButton>(R.id.btn_confirm_sale)

        // Populate product dropdown
        val productNames = productList.map { it.name }
        val dropdownAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, productNames)
        actProduct.setAdapter(dropdownAdapter)

        var selectedProduct: Product? = null

        actProduct.setOnItemClickListener { _, _, position, _ ->
            val name = dropdownAdapter.getItem(position) ?: return@setOnItemClickListener
            selectedProduct = productList.find { it.name == name }
            selectedProduct?.let { p ->
                updateProfitPreview(p, etQuantity.text.toString(), tvUnitProfit, tvTotalRevenue, tvTotalProfit, tvMarginLabel)
            }
        }

        etQuantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                tilQuantity.error = null
                selectedProduct?.let { p ->
                    updateProfitPreview(p, s.toString(), tvUnitProfit, tvTotalRevenue, tvTotalProfit, tvMarginLabel)
                }
            }
        })

        btnConfirm.setOnClickListener {
            val product = selectedProduct
            if (product == null) {
                Toast.makeText(requireContext(), "Please select a product", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val qtyStr = etQuantity.text.toString().trim()
            val qty = qtyStr.toIntOrNull()
            if (qty == null || qty <= 0) {
                tilQuantity.error = "Enter a valid quantity"
                return@setOnClickListener
            }

            if (qty > product.stockQuantity) {
                tilQuantity.error = "Only ${product.stockQuantity} units available"
                return@setOnClickListener
            }

            val totalRevenue = product.sellingPrice * qty
            val totalProfit = ProfitCalculator.calculateProfit(product.purchasePrice, product.sellingPrice, qty)
            val marginPercent = ProfitCalculator.calculateMarginPercent(product.purchasePrice, product.sellingPrice)

            val sale = Sale(
                productId = product.id,
                productName = product.name,
                quantitySold = qty,
                purchasePrice = product.purchasePrice,
                sellingPrice = product.sellingPrice,
                totalRevenue = totalRevenue,
                totalProfit = totalProfit,
                profitMarginPercent = marginPercent
            )

            saleViewModel.recordSale(sale)
            val newStock = product.stockQuantity - qty
            saleViewModel.updateStock(product.id, newStock)

            // Check if low stock alert needed
            if (newStock <= product.lowStockThreshold) {
                val alert = StockAlert(
                    productId = product.id,
                    productName = product.name,
                    currentStock = newStock,
                    threshold = product.lowStockThreshold
                )
                saleViewModel.insertStockAlert(alert)
            }

            dialog.dismiss()
            Toast.makeText(requireContext(), "Sale recorded successfully!", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }

    private fun updateProfitPreview(
        product: Product,
        qtyStr: String,
        tvUnitProfit: TextView,
        tvTotalRevenue: TextView,
        tvTotalProfit: TextView,
        tvMarginLabel: TextView
    ) {
        val qty = qtyStr.toIntOrNull() ?: 0
        val unitProfit = product.sellingPrice - product.purchasePrice
        val totalRev = product.sellingPrice * qty
        val totalProf = ProfitCalculator.calculateProfit(product.purchasePrice, product.sellingPrice, qty)
        val margin = ProfitCalculator.calculateMarginPercent(product.purchasePrice, product.sellingPrice)

        tvUnitProfit.text = "Unit Profit: Rs. ${String.format("%,.0f", unitProfit)}"
        tvTotalRevenue.text = "Total Revenue: Rs. ${String.format("%,.0f", totalRev)}"
        tvTotalProfit.text = "Total Profit: Rs. ${String.format("%,.0f", totalProf)}"

        val label = ProfitCalculator.getMarginLabel(margin)
        tvMarginLabel.text = "$label (${String.format("%.1f", margin)}%)"
        val colorRes = ProfitCalculator.getMarginColorRes(margin)
        tvMarginLabel.setTextColor(ContextCompat.getColor(requireContext(), colorRes))
    }
}
