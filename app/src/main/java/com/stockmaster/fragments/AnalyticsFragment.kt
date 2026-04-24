package com.stockmaster.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
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

        // L9 — RadioGroup for Daily/Weekly/Monthly toggle
        val periodToggle = view.findViewById<RadioGroup>(R.id.rg_period)
        val tvRevenue = view.findViewById<TextView>(R.id.tv_revenue_amount)

        periodToggle?.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_daily -> {
                    tvRevenue?.text = "$42,890.00"
                }
                R.id.rb_weekly -> {
                    tvRevenue?.text = "$298,230.00"
                }
                R.id.rb_monthly -> {
                    tvRevenue?.text = "$1,155,760.00"
                }
            }
        }
    }
}
