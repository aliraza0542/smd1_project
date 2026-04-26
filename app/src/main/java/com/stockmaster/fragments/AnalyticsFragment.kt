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
import com.stockmaster.R

// F4 — Fragment Transaction target for bottom nav
class AnalyticsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_analytics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // L9 — RadioGroup for Daily/Yearly/Monthly toggle
        val periodToggle = view.findViewById<RadioGroup>(R.id.rg_period)
        val rbDaily = view.findViewById<RadioButton>(R.id.rb_daily)
        val rbWeekly = view.findViewById<RadioButton>(R.id.rb_weekly)
        val rbMonthly = view.findViewById<RadioButton>(R.id.rb_monthly)
        val tvRevenue = view.findViewById<TextView>(R.id.tv_revenue_amount)

        periodToggle?.setOnCheckedChangeListener { _, checkedId ->
            updatePeriodToggleUi(rbDaily, rbWeekly, rbMonthly, checkedId)
            when (checkedId) {
                R.id.rb_daily -> tvRevenue?.text = "$42,890.00"
                R.id.rb_weekly -> tvRevenue?.text = "$298,230.00"
                R.id.rb_monthly -> tvRevenue?.text = "$1,155,760.00"
            }
        }

        // Ensure the selected button style matches the default checked option on first render.
        updatePeriodToggleUi(rbDaily, rbWeekly, rbMonthly, periodToggle?.checkedRadioButtonId ?: R.id.rb_daily)
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
