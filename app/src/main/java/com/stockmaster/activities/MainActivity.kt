package com.stockmaster.activities

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

    private var activeNavIndex = 0 // Default: Dashboard section of combined home page

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Extract Intent Extras
        userName = intent.getStringExtra("USER_NAME") ?: "Manager"
        userEmail = intent.getStringExtra("USER_EMAIL") ?: ""
        userRole = intent.getStringExtra("USER_ROLE") ?: "Staff"
        storeId = intent.getIntExtra("STORE_ID", 0)

        setupBottomNav()

        // Load default fragment (combined Dashboard+Inventory page) on first launch only
        if (savedInstanceState == null) {
            loadFragment(InventoryFragment.newInstance(openInventorySection = false), 0)
        }
    }

    // F4 — Fragment Transactions: Bottom nav switches fragments without Activity restart
    private fun setupBottomNav() {
        val navDashboard = findViewById<android.widget.LinearLayout>(R.id.nav_dashboard)
        val navInventory = findViewById<android.widget.LinearLayout>(R.id.nav_inventory)
        val navPos = findViewById<android.widget.LinearLayout>(R.id.nav_pos)
        val navAnalytics = findViewById<android.widget.LinearLayout>(R.id.nav_analytics)

        navDashboard.setOnClickListener {
            val current = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (current is InventoryFragment) {
                current.scrollToDashboardSection()
                updateHomeNavHighlight(0)
            } else {
                loadFragment(InventoryFragment.newInstance(openInventorySection = false), 0)
            }
        }
        navInventory.setOnClickListener {
            val current = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (current is InventoryFragment) {
                current.scrollToInventorySection()
                updateHomeNavHighlight(1)
            } else {
                loadFragment(InventoryFragment.newInstance(openInventorySection = true), 1)
            }
        }
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
        val navLabelIds = listOf(
            R.id.nav_dashboard_label,
            R.id.nav_inventory_label,
            R.id.nav_pos_label,
            R.id.nav_analytics_label
        )

        navIds.forEachIndexed { i, id ->
            val view = findViewById<android.widget.LinearLayout>(id)
            view?.alpha = if (i == activeIndex) 1.0f else 0.6f
        }

        navLabelIds.forEachIndexed { i, id ->
            val label = findViewById<android.widget.TextView>(id)
            val isActive = i == activeIndex
            label?.setTextColor(
                ContextCompat.getColor(
                    this,
                    if (isActive) R.color.colorPrimaryDark else R.color.colorTextSecondary
                )
            )
            label?.setTypeface(null, if (isActive) Typeface.BOLD else Typeface.NORMAL)
        }
    }

    // F4 — InventoryFragment calls this while scrolling to keep Dashboard/Inventory nav state in sync.
    fun updateHomeNavHighlight(index: Int) {
        if (index !in 0..1) return
        if (activeNavIndex == 2 || activeNavIndex == 3) return
        activeNavIndex = index
        updateNavHighlight(index)
    }

    fun getUserName(): String = userName
    fun getUserEmail(): String = userEmail
    fun getUserRole(): String = userRole
    fun getStoreId(): Int = storeId
}
