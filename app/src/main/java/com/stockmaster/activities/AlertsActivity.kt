package com.stockmaster.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.stockmaster.R
import com.stockmaster.adapters.AlertAdapter
import com.stockmaster.models.StockAlert
import com.stockmaster.viewmodels.AlertsViewModel

class AlertsActivity : AppCompatActivity() {

    private lateinit var viewModel: AlertsViewModel
    private lateinit var alertAdapter: AlertAdapter
    private lateinit var rvAlerts: RecyclerView
    private lateinit var layoutEmptyAlerts: LinearLayout
    private lateinit var btnClearAll: MaterialButton
    private var userRole: String = "STAFF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alerts)

        viewModel = ViewModelProvider(this)[AlertsViewModel::class.java]
        userRole = intent.getStringExtra("USER_ROLE") ?: "STAFF"

        val btnBack = findViewById<ImageButton>(R.id.btn_back_alerts)
        rvAlerts = findViewById(R.id.rv_alerts)
        layoutEmptyAlerts = findViewById(R.id.layout_empty_alerts)
        btnClearAll = findViewById(R.id.btn_clear_all_alerts)

        // Admin-only clear all
        if (userRole != "ADMIN") {
            btnClearAll.visibility = View.GONE
        }

        btnBack.setOnClickListener { finish() }

        alertAdapter = AlertAdapter(this) { alert, position ->
            markAlertAsRead(alert, position)
        }
        rvAlerts.layoutManager = LinearLayoutManager(this)
        rvAlerts.adapter = alertAdapter

        loadAlerts()

        btnClearAll.setOnClickListener {
            clearAllAlerts()
        }
    }

    private fun loadAlerts() {
        viewModel.allAlerts.observe(this) { alerts ->
            alertAdapter.setData(alerts)
            if (alerts.isEmpty()) {
                rvAlerts.visibility = View.GONE
                layoutEmptyAlerts.visibility = View.VISIBLE
            } else {
                rvAlerts.visibility = View.VISIBLE
                layoutEmptyAlerts.visibility = View.GONE
            }
        }
    }

    private fun markAlertAsRead(alert: StockAlert, position: Int) {
        viewModel.markAsRead(alert.id)
        alertAdapter.markRead(position)
    }

    private fun clearAllAlerts() {
        viewModel.deleteAll()
    }
}
