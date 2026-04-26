package com.stockmaster.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.stockmaster.R
import com.stockmaster.database.DatabaseHelper
import com.stockmaster.database.dao.ProductDao
import com.stockmaster.database.dao.SaleDao
import com.stockmaster.network.ApiClient
import com.stockmaster.repository.ProductRepository
import kotlinx.coroutines.launch

// F4 — Fragment Transaction target for bottom nav
class AnalyticsFragment : Fragment() {

    private lateinit var repository: ProductRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_analytics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHelper = DatabaseHelper.getInstance(requireContext())
        repository = ProductRepository(
            productDao = ProductDao(dbHelper),
            saleDao = SaleDao(dbHelper),
            apiService = ApiClient.apiService
        )

        // L9 — RadioGroup for Daily/Yearly/Monthly toggle
        val periodToggle = view.findViewById<RadioGroup>(R.id.rg_period)
        val rbDaily = view.findViewById<RadioButton>(R.id.rb_daily)
        val rbWeekly = view.findViewById<RadioButton>(R.id.rb_weekly)
        val rbMonthly = view.findViewById<RadioButton>(R.id.rb_monthly)
        val tvRevenue = view.findViewById<TextView>(R.id.tv_revenue_amount)
        val tvUnitsSold = view.findViewById<TextView>(R.id.tv_units_sold)
        val tvTopCategory = view.findViewById<TextView>(R.id.tv_top_category)

        periodToggle?.setOnCheckedChangeListener { _, checkedId ->
            updatePeriodToggleUi(rbDaily, rbWeekly, rbMonthly, checkedId)
            when (checkedId) {
                R.id.rb_daily -> tvRevenue?.alpha = 1.0f
                R.id.rb_weekly -> tvRevenue?.alpha = 0.95f
                R.id.rb_monthly -> tvRevenue?.alpha = 0.9f
            }
        }

        // Ensure the selected button style matches the default checked option on first render.
        updatePeriodToggleUi(rbDaily, rbWeekly, rbMonthly, periodToggle?.checkedRadioButtonId ?: R.id.rb_daily)

        lifecycleScope.launch {
            val sales = repository.getAllSales()
            val revenue = sales.sumOf { it.salePrice * it.quantitySold }
            val units = sales.sumOf { it.quantitySold }

            val topCategory = repository
                .getLocalProducts("", "all", ProductRepository.SORT_NAME_ASC)
                .groupBy { it.category }
                .maxByOrNull { (_, products) -> products.size }
                ?.key ?: getString(R.string.not_available)

            tvRevenue?.text = "$${String.format("%,.2f", revenue)}"
            tvUnitsSold?.text = units.toString()
            tvTopCategory?.text = topCategory
        }
    }

    private fun updatePeriodToggleUi(
        rbDaily: RadioButton,
        rbWeekly: RadioButton,
        rbMonthly: RadioButton,
        checkedId: Int
    ) {
        val activeBackground = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_active)
        val inactiveBackground = ContextCompat.getDrawable(requireContext(), android.R.color.transparent)
        val activeTextColor = ContextCompat.getColor(requireContext(), R.color.white)
        val inactiveTextColor = ContextCompat.getColor(requireContext(), R.color.colorTextSecondary)

        listOf(rbDaily, rbWeekly, rbMonthly).forEach { radioButton ->
            val isActive = radioButton.id == checkedId
            radioButton.background = if (isActive) activeBackground else inactiveBackground
            radioButton.setTextColor(if (isActive) activeTextColor else inactiveTextColor)
        }
    }
}
