package com.stockmaster.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.stockmaster.R
import com.stockmaster.activities.MainActivity
import com.stockmaster.adapters.InventoryAdapter
import com.stockmaster.models.Product

// F3 — RecyclerView in activity_inventory with InventoryAdapter
// F4 — Fragment Transaction target for bottom nav
// F5 — Search/Filter: filters RecyclerView items in real-time
class InventoryFragment : Fragment() {

    companion object {
        private const val ARG_OPEN_INVENTORY_SECTION = "open_inventory_section"

        fun newInstance(openInventorySection: Boolean): InventoryFragment {
            return InventoryFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_OPEN_INVENTORY_SECTION, openInventorySection)
                }
            }
        }
    }

    private lateinit var adapter: InventoryAdapter
    private var allProducts: List<Product> = Product.getSampleProducts()
    private var activeFilter: String = "All Items"

    // F5 — Keep explicit refs so filter chip visuals stay in sync with activeFilter
    private var chipAllView: TextView? = null
    private var chipLowStockView: TextView? = null
    private var chipElectronicsView: TextView? = null
    private var appBarLayout: AppBarLayout? = null
    private var inventoryRecyclerView: RecyclerView? = null
    private var lastHomeSectionIndex: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view)
        setupSearch(view)
        setupFilterChips(view)
        setupHomeSectionSync(view)
        applyInitialSectionIfNeeded()
    }

    // F3 — RecyclerView setup with InventoryAdapter
    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_inventory)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        inventoryRecyclerView = recyclerView

        // F2 — Clicking item opens ItemDetailFragment, passes product via Bundle
        adapter = InventoryAdapter(allProducts) { product ->
            val fragment = ItemDetailFragment()
            val bundle = Bundle().apply {
                putParcelable("product", product)
            }
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerView.adapter = adapter
    }

    // F4 — Combined Dashboard+Inventory page: switch nav highlight based on scroll position.
    private fun setupHomeSectionSync(view: View) {
        appBarLayout = view.findViewById(R.id.app_bar_layout)

        appBarLayout?.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, _ ->
            updateHomeSectionByScrollState()
        })

        inventoryRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                updateHomeSectionByScrollState()
            }
        })
    }

    private fun applyInitialSectionIfNeeded() {
        val openInventorySection = arguments?.getBoolean(ARG_OPEN_INVENTORY_SECTION, false) ?: false
        if (openInventorySection) {
            scrollToInventorySection()
            notifyHomeSection(1)
        } else {
            scrollToDashboardSection()
            notifyHomeSection(0)
        }
    }

    fun scrollToDashboardSection() {
        appBarLayout?.setExpanded(true, true)
        inventoryRecyclerView?.scrollToPosition(0)
        notifyHomeSection(0)
    }

    fun scrollToInventorySection() {
        inventoryRecyclerView?.post {
            appBarLayout?.setExpanded(false, true)
            inventoryRecyclerView?.scrollToPosition(0)
            notifyHomeSection(1)
        }
    }

    private fun updateHomeSectionByScrollState() {
        val appBarFullyExpanded = appBarLayout?.top == 0
        val recyclerAtTop = inventoryRecyclerView?.let { rv ->
            val lm = rv.layoutManager as? LinearLayoutManager
            lm?.findFirstVisibleItemPosition() == 0 && rv.computeVerticalScrollOffset() == 0
        } ?: true

        val sectionIndex = if (appBarFullyExpanded && recyclerAtTop) 0 else 1
        notifyHomeSection(sectionIndex)
    }

    private fun notifyHomeSection(index: Int) {
        if (lastHomeSectionIndex == index) return
        lastHomeSectionIndex = index
        (activity as? MainActivity)?.updateHomeNavHighlight(index)
    }

    // F5 — Real-time search filter
    private fun setupSearch(view: View) {
        val searchBar = view.findViewById<EditText>(R.id.et_search)
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterProducts(s.toString())
            }
        })
    }

    // F5 — Filter by chip selection
    private fun setupFilterChips(view: View) {
        chipAllView = view.findViewById(R.id.chip_all)
        chipLowStockView = view.findViewById(R.id.chip_low_stock)
        chipElectronicsView = view.findViewById(R.id.chip_electronics)

        chipAllView?.setOnClickListener {
            activeFilter = "All Items"
            updateFilterChipUi()
            filterProducts("")
        }
        chipLowStockView?.setOnClickListener {
            activeFilter = "Low Stock"
            updateFilterChipUi()
            filterProducts("")
        }
        chipElectronicsView?.setOnClickListener {
            activeFilter = "Electronics"
            updateFilterChipUi()
            filterProducts("")
        }

        updateFilterChipUi()
    }

    private fun updateFilterChipUi() {
        val chipActiveBackground = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_active)
        val chipInactiveBackground = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_inactive)
        val activeTextColor = ContextCompat.getColor(requireContext(), R.color.white)
        val inactiveTextColor = ContextCompat.getColor(requireContext(), R.color.colorTextSecondary)

        val chips = listOf(
            "All Items" to chipAllView,
            "Low Stock" to chipLowStockView,
            "Electronics" to chipElectronicsView
        )

        chips.forEach { (filterName, chip) ->
            val isActive = activeFilter == filterName
            chip?.background = if (isActive) chipActiveBackground else chipInactiveBackground
            chip?.setTextColor(if (isActive) activeTextColor else inactiveTextColor)
        }
    }

    private fun filterProducts(query: String) {
        var filtered = when (activeFilter) {
            "Low Stock" -> allProducts.filter { it.status == "critical" || it.status == "low" }
            "Electronics" -> allProducts.filter { it.category == "Electronics" }
            else -> allProducts
        }
        if (query.isNotEmpty()) {
            filtered = filtered.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        adapter.updateList(filtered)
    }
}
