package com.stockmaster.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.stockmaster.R
import com.stockmaster.fragments.AnalyticsFragment
import com.stockmaster.fragments.InventoryFragment
import com.stockmaster.fragments.POSFragment

class MainActivity : AppCompatActivity() {

    // F1 — Receives user info passed via Intent Extras from SplashActivity
    private var userName: String = ""
    private var userEmail: String = ""
    private var userRole: String = ""
    private var storeId: Int = 0

    private var activeNavIndex = 1 // Default: Inventory tab

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Extract Intent Extras
        userName = intent.getStringExtra("USER_NAME") ?: "Manager"
        userEmail = intent.getStringExtra("USER_EMAIL") ?: ""
        userRole = intent.getStringExtra("USER_ROLE") ?: "Staff"
        storeId = intent.getIntExtra("STORE_ID", 0)

        setupBottomNav()

        // Load default fragment (Inventory) on first launch only
        if (savedInstanceState == null) {
            loadFragment(InventoryFragment(), 1)
        }
    }

    // F4 — Fragment Transactions: Bottom nav switches fragments without Activity restart
    private fun setupBottomNav() {
        val navDashboard = findViewById<android.widget.LinearLayout>(R.id.nav_dashboard)
        val navInventory = findViewById<android.widget.LinearLayout>(R.id.nav_inventory)
        val navPos = findViewById<android.widget.LinearLayout>(R.id.nav_pos)
        val navAnalytics = findViewById<android.widget.LinearLayout>(R.id.nav_analytics)

        navDashboard.setOnClickListener { loadFragment(InventoryFragment(), 0) }
        navInventory.setOnClickListener { loadFragment(InventoryFragment(), 1) }
        navPos.setOnClickListener { loadFragment(POSFragment(), 2) }
        navAnalytics.setOnClickListener { loadFragment(AnalyticsFragment(), 3) }

        updateNavHighlight(activeNavIndex)
    }

    private fun loadFragment(fragment: Fragment, index: Int) {
        activeNavIndex = index
        updateNavHighlight(index)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun updateNavHighlight(activeIndex: Int) {
        val navIds = listOf(
            R.id.nav_dashboard,
            R.id.nav_inventory,
            R.id.nav_pos,
            R.id.nav_analytics
        )
        navIds.forEachIndexed { i, id ->
            val view = findViewById<android.widget.LinearLayout>(id)
            view?.alpha = if (i == activeIndex) 1.0f else 0.5f
        }
    }

    fun getUserName(): String = userName
    fun getUserEmail(): String = userEmail
    fun getUserRole(): String = userRole
    fun getStoreId(): Int = storeId
}
