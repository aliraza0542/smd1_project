package com.stockmaster.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.datepicker.MaterialDatePicker
import com.stockmaster.R
import com.stockmaster.utils.DateUtils
import com.stockmaster.viewmodels.SaleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AnalyticsFragment : Fragment() {

    private lateinit var saleViewModel: SaleViewModel

    private lateinit var btnFromDate: MaterialButton
    private lateinit var btnToDate: MaterialButton
    private lateinit var chipGroupReportType: ChipGroup
    private lateinit var btnGenerate: MaterialButton
    private lateinit var tvReportOutput: TextView
    private lateinit var btnShare: MaterialButton

    private var fromDate: Long = DateUtils.getStartOfDay() - (7 * 86400000L)
    private var toDate: Long = DateUtils.getEndOfDay()
    private var reportType: String = "Sales Summary"
    private var reportText: String = ""
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
        return inflater.inflate(R.layout.fragment_analytics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saleViewModel = ViewModelProvider(this)[SaleViewModel::class.java]

        btnFromDate = view.findViewById(R.id.btn_from_date)
        btnToDate = view.findViewById(R.id.btn_to_date)
        chipGroupReportType = view.findViewById(R.id.chip_group_report_type)
        btnGenerate = view.findViewById(R.id.btn_generate_report)
        tvReportOutput = view.findViewById(R.id.tv_report_output)
        btnShare = view.findViewById(R.id.btn_share_report)

        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        btnFromDate.text = "From: ${dateFormat.format(Date(fromDate))}"
        btnToDate.text = "To: ${dateFormat.format(Date(toDate))}"

        btnFromDate.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select start date")
                .setSelection(fromDate)
                .build()
            picker.addOnPositiveButtonClickListener { selection ->
                fromDate = selection
                btnFromDate.text = "From: ${dateFormat.format(Date(fromDate))}"
            }
            picker.show(parentFragmentManager, "FROM_DATE")
        }

        btnToDate.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select end date")
                .setSelection(toDate)
                .build()
            picker.addOnPositiveButtonClickListener { selection ->
                toDate = selection + 86399999L // End of day
                btnToDate.text = "To: ${dateFormat.format(Date(toDate))}"
            }
            picker.show(parentFragmentManager, "TO_DATE")
        }

        chipGroupReportType.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val chip = view.findViewById<Chip>(checkedIds[0])
                reportType = chip?.text?.toString() ?: "Sales Summary"
            }
        }

        btnGenerate.setOnClickListener {
            generateReport()
        }

        btnShare.setOnClickListener {
            if (reportText.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, reportText)
                startActivity(Intent.createChooser(intent, "Share Report"))
            } else {
                Toast.makeText(requireContext(), "Generate a report first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateReport() {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                when (reportType) {
                    "Sales Summary" -> generateSalesSummary()
                    "Profit Report" -> generateProfitReport()
                    "Stock Report" -> generateStockReport()
                    else -> generateSalesSummary()
                }
            }
            reportText = result
            tvReportOutput.text = result
            btnShare.visibility = View.VISIBLE
        }
    }

    private suspend fun generateSalesSummary(): String {
        val sales = saleViewModel.getSalesBetween(fromDate, toDate)
        val totalRevenue = saleViewModel.getRevenueBetween(fromDate, toDate) ?: 0.0
        val totalProfit = saleViewModel.getProfitBetween(fromDate, toDate) ?: 0.0
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val avgMargin = if (sales.isNotEmpty()) {
            sales.sumOf { it.profitMarginPercent } / sales.size
        } else 0.0

        val totalQty = sales.sumOf { it.quantitySold }

        val sb = StringBuilder()
        sb.appendLine("═══════════════════════════════════")
        sb.appendLine("   STOCKMASTER SALES SUMMARY")
        sb.appendLine("═══════════════════════════════════")
        sb.appendLine()
        sb.appendLine("Period: ${dateFormat.format(Date(fromDate))} - ${dateFormat.format(Date(toDate))}")
        sb.appendLine()
        sb.appendLine("Total Revenue:    Rs. ${String.format("%,.0f", totalRevenue)}")
        sb.appendLine("Total Profit:     Rs. ${String.format("%,.0f", totalProfit)}")
        sb.appendLine("Total Items Sold: $totalQty")
        sb.appendLine("Transactions:     ${sales.size}")
        sb.appendLine("Avg Margin:       ${String.format("%.1f", avgMargin)}%")
        sb.appendLine()
        sb.appendLine("───────────────────────────────────")
        sb.appendLine("   TRANSACTION DETAILS")
        sb.appendLine("───────────────────────────────────")

        sales.forEachIndexed { index, sale ->
            sb.appendLine()
            sb.appendLine("${index + 1}. ${sale.productName}")
            sb.appendLine("   Qty: ${sale.quantitySold} × Rs.${String.format("%,.0f", sale.sellingPrice)}")
            sb.appendLine("   Revenue: Rs.${String.format("%,.0f", sale.totalRevenue)}")
            sb.appendLine("   Profit:  Rs.${String.format("%,.0f", sale.totalProfit)} (${String.format("%.1f", sale.profitMarginPercent)}%)")
            sb.appendLine("   Date:    ${dateFormat.format(Date(sale.saleDate))}")
        }

        sb.appendLine()
        sb.appendLine("═══════════════════════════════════")
        sb.appendLine("Generated by StockMaster")
        sb.appendLine("${dateFormat.format(Date())}")

        return sb.toString()
    }

    private suspend fun generateProfitReport(): String {
        val sales = saleViewModel.getSalesBetween(fromDate, toDate)
        val totalRevenue = saleViewModel.getRevenueBetween(fromDate, toDate) ?: 0.0
        val totalProfit = saleViewModel.getProfitBetween(fromDate, toDate) ?: 0.0
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val totalCost = totalRevenue - totalProfit
        val overallMargin = if (totalRevenue > 0) (totalProfit / totalRevenue) * 100.0 else 0.0

        // Group by product
        val byProduct = sales.groupBy { it.productName }

        val sb = StringBuilder()
        sb.appendLine("═══════════════════════════════════")
        sb.appendLine("   STOCKMASTER PROFIT REPORT")
        sb.appendLine("═══════════════════════════════════")
        sb.appendLine()
        sb.appendLine("Period: ${dateFormat.format(Date(fromDate))} - ${dateFormat.format(Date(toDate))}")
        sb.appendLine()
        sb.appendLine("Total Revenue:     Rs. ${String.format("%,.0f", totalRevenue)}")
        sb.appendLine("Total Cost:        Rs. ${String.format("%,.0f", totalCost)}")
        sb.appendLine("Net Profit:        Rs. ${String.format("%,.0f", totalProfit)}")
        sb.appendLine("Overall Margin:    ${String.format("%.1f", overallMargin)}%")
        sb.appendLine()
        sb.appendLine("───────────────────────────────────")
        sb.appendLine("   PROFIT BY PRODUCT")
        sb.appendLine("───────────────────────────────────")

        byProduct.forEach { (name, productSales) ->
            val productProfit = productSales.sumOf { it.totalProfit }
            val productRevenue = productSales.sumOf { it.totalRevenue }
            val productMargin = if (productRevenue > 0) (productProfit / productRevenue) * 100.0 else 0.0
            sb.appendLine()
            sb.appendLine("• $name")
            sb.appendLine("  Revenue: Rs.${String.format("%,.0f", productRevenue)} | Profit: Rs.${String.format("%,.0f", productProfit)} | Margin: ${String.format("%.1f", productMargin)}%")
        }

        sb.appendLine()
        sb.appendLine("═══════════════════════════════════")
        sb.appendLine("Generated by StockMaster")
        sb.appendLine("${dateFormat.format(Date())}")

        return sb.toString()
    }

    private suspend fun generateStockReport(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val products = withContext(Dispatchers.IO) {
            val db = com.stockmaster.database.AppDatabase.getDatabase(requireContext().applicationContext)
            db.productDao().getAllProductsList()
        }

        val totalItems = products.sumOf { it.stockQuantity }
        val totalValue = products.sumOf { it.sellingPrice * it.stockQuantity }
        val lowStock = products.filter { it.stockQuantity in 1..it.lowStockThreshold }
        val outOfStock = products.filter { it.stockQuantity == 0 }

        val sb = StringBuilder()
        sb.appendLine("═══════════════════════════════════")
        sb.appendLine("   STOCKMASTER STOCK REPORT")
        sb.appendLine("═══════════════════════════════════")
        sb.appendLine()
        sb.appendLine("Report Date: ${dateFormat.format(Date())}")
        sb.appendLine()
        sb.appendLine("Total Products:     ${products.size}")
        sb.appendLine("Total Stock Units:  $totalItems")
        sb.appendLine("Inventory Value:    Rs. ${String.format("%,.0f", totalValue)}")
        sb.appendLine("Low Stock Items:    ${lowStock.size}")
        sb.appendLine("Out of Stock Items: ${outOfStock.size}")
        sb.appendLine()
        sb.appendLine("───────────────────────────────────")
        sb.appendLine("   ALL PRODUCTS")
        sb.appendLine("───────────────────────────────────")

        products.forEach { p ->
            val status = when {
                p.stockQuantity == 0 -> "OUT OF STOCK"
                p.stockQuantity <= p.lowStockThreshold -> "LOW STOCK"
                else -> "IN STOCK"
            }
            sb.appendLine()
            sb.appendLine("• ${p.name} [${p.category}]")
            sb.appendLine("  Stock: ${p.stockQuantity} units | Status: $status")
            sb.appendLine("  Value: Rs.${String.format("%,.0f", p.sellingPrice * p.stockQuantity)}")
        }

        if (lowStock.isNotEmpty()) {
            sb.appendLine()
            sb.appendLine("───────────────────────────────────")
            sb.appendLine("   ⚠ LOW STOCK ALERTS")
            sb.appendLine("───────────────────────────────────")
            lowStock.forEach { p ->
                sb.appendLine("  • ${p.name}: ${p.stockQuantity}/${p.lowStockThreshold} units")
            }
        }

        if (outOfStock.isNotEmpty()) {
            sb.appendLine()
            sb.appendLine("───────────────────────────────────")
            sb.appendLine("   ✖ OUT OF STOCK")
            sb.appendLine("───────────────────────────────────")
            outOfStock.forEach { p ->
                sb.appendLine("  • ${p.name}")
            }
        }

        sb.appendLine()
        sb.appendLine("═══════════════════════════════════")
        sb.appendLine("Generated by StockMaster")
        sb.appendLine("${dateFormat.format(Date())}")

        return sb.toString()
    }
}
