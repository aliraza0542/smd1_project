package com.stockmaster.fragments

import android.os.Handler
import android.os.Looper
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.stockmaster.R
import com.stockmaster.activities.MainActivity
import com.stockmaster.adapters.InventoryAdapter
import com.stockmaster.database.DatabaseHelper
import com.stockmaster.database.dao.ProductDao
import com.stockmaster.database.dao.SaleDao
import com.stockmaster.models.Product
import com.stockmaster.network.ApiClient
import com.stockmaster.repository.ProductRepository
import kotlinx.coroutines.launch

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
    private var allProducts: List<Product> = emptyList()
    private var activeFilter: String = "All Items"
    private var searchQuery: String = ""
    private var sortOption: String = ProductRepository.SORT_NAME_ASC
    private lateinit var repository: ProductRepository

    // F5 — Keep explicit refs so filter chip visuals stay in sync with activeFilter
    private var chipAllView: TextView? = null
    private var chipInStockView: TextView? = null
    private var chipLowStockView: TextView? = null
    private var chipCriticalView: TextView? = null
    private var appBarLayout: AppBarLayout? = null
    private var inventoryRecyclerView: RecyclerView? = null
    private var emptyStateText: TextView? = null
    private var loadingView: ProgressBar? = null
    private var lowStockBanner: View? = null
    private var lowStockBannerText: TextView? = null
    private var lowStockViewAction: TextView? = null
    private var topProductsText: TextView? = null
    private var pendingDelete: Pair<Product, Int>? = null
    private val deleteHandler = Handler(Looper.getMainLooper())
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

        val dbHelper = DatabaseHelper.getInstance(requireContext())
        repository = ProductRepository(
            productDao = ProductDao(dbHelper),
            saleDao = SaleDao(dbHelper),
            apiService = ApiClient.apiService
        )

        setupRecyclerView(view)
        setupSearch(view)
        setupFilterChips(view)
        setupSort(view)
        setupFab(view)
        setupFormResultListener()
        setupHomeSectionSync(view)
        setupSwipeActions(view)
        setupDashboardHeader(view)
        applyInitialSectionIfNeeded()
        loadProducts()
    }

    // F3 — RecyclerView setup with InventoryAdapter
    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_inventory)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        inventoryRecyclerView = recyclerView
        emptyStateText = view.findViewById(R.id.tv_inventory_empty)
        loadingView = view.findViewById(R.id.inventory_progress)
        lowStockBanner = view.findViewById(R.id.low_stock_banner)
        lowStockBannerText = view.findViewById(R.id.tv_low_stock_banner)
        lowStockViewAction = view.findViewById(R.id.tv_low_stock_view)
        topProductsText = view.findViewById(R.id.tv_top_products)

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
        } { product ->
            AddEditProductFragment.newInstance(product)
                .show(parentFragmentManager, "edit_product")
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
                searchQuery = s.toString().trim()
                loadProducts()
            }
        })
    }

    // F5 — Filter by chip selection
    private fun setupFilterChips(view: View) {
        chipAllView = view.findViewById(R.id.chip_all)
        chipInStockView = view.findViewById(R.id.chip_in_stock)
        chipLowStockView = view.findViewById(R.id.chip_low_stock)
        chipCriticalView = view.findViewById(R.id.chip_critical)

        chipAllView?.setOnClickListener {
            activeFilter = "All Items"
            updateFilterChipUi()
            loadProducts()
        }
        chipInStockView?.setOnClickListener {
            activeFilter = "In Stock"
            updateFilterChipUi()
            loadProducts()
        }
        chipLowStockView?.setOnClickListener {
            activeFilter = "Low Stock"
            updateFilterChipUi()
            loadProducts()
        }
        chipCriticalView?.setOnClickListener {
            activeFilter = "Critical"
            updateFilterChipUi()
            loadProducts()
        }

        updateFilterChipUi()
    }

    private fun setupSort(view: View) {
        val spinner = view.findViewById<Spinner>(R.id.spinner_sort)
        spinner.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: View?, position: Int, id: Long) {
                sortOption = when (position) {
                    1 -> ProductRepository.SORT_STOCK_ASC
                    2 -> ProductRepository.SORT_STOCK_DESC
                    3 -> ProductRepository.SORT_PRICE_ASC
                    4 -> ProductRepository.SORT_PRICE_DESC
                    else -> ProductRepository.SORT_NAME_ASC
                }
                loadProducts()
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) = Unit
        }
    }

    private fun setupFab(view: View) {
        view.findViewById<View>(R.id.fab_add)?.setOnClickListener {
            AddEditProductFragment.newInstance().show(parentFragmentManager, "add_product")
        }
    }

    private fun setupFormResultListener() {
        parentFragmentManager.setFragmentResultListener(
            AddEditProductFragment.REQUEST_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            if (bundle.getBoolean(AddEditProductFragment.BUNDLE_REFRESH)) {
                loadProducts()
            }
        }
    }

    private fun setupSwipeActions(view: View) {
        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val product = adapter.getItem(position)
                if (direction == ItemTouchHelper.RIGHT) {
                    adapter.notifyItemChanged(position)
                    AddEditProductFragment.newInstance(product).show(parentFragmentManager, "edit_product_swipe")
                } else {
                    pendingDelete = product to position
                    val preview = adapter.getItems().toMutableList().apply { removeAt(position) }
                    adapter.updateList(preview)

                    val snackbar = Snackbar.make(view, R.string.product_deleted, Snackbar.LENGTH_LONG)
                    snackbar.setAction(R.string.undo) {
                        deleteHandler.removeCallbacksAndMessages(null)
                        pendingDelete = null
                        loadProducts()
                    }
                    snackbar.show()

                    deleteHandler.postDelayed({
                        val pending = pendingDelete ?: return@postDelayed
                        lifecycleScope.launch {
                            repository.deleteProduct(pending.first.id)
                            pendingDelete = null
                            loadProducts()
                        }
                    }, 3000)
                }
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(inventoryRecyclerView)
    }

    private fun setupDashboardHeader(view: View) {
        val titleView = view.findViewById<TextView>(R.id.tv_topbar_title)
        val userName = (activity as? MainActivity)?.getUserName().orEmpty()
        if (userName.isNotBlank()) {
            titleView?.text = getString(R.string.welcome_user, userName)
        }

        // Phase 3 — Low stock banner quick action opens inventory in low-stock context.
        lowStockViewAction?.setOnClickListener {
            openLowStockContext()
        }
    }

    private fun openLowStockContext() {
        activeFilter = "Low Stock"
        searchQuery = ""
        sortOption = ProductRepository.SORT_STOCK_ASC
        view?.findViewById<Spinner>(R.id.spinner_sort)?.setSelection(1)
        view?.findViewById<EditText>(R.id.et_search)?.setText("")
        updateFilterChipUi()
        scrollToInventorySection()
        loadProducts()
    }

    private fun updateFilterChipUi() {
        val chipActiveBackground = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_active)
        val chipInactiveBackground = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_inactive)
        val activeTextColor = ContextCompat.getColor(requireContext(), R.color.white)
        val inactiveTextColor = ContextCompat.getColor(requireContext(), R.color.colorTextSecondary)

        val chips = listOf(
            "All Items" to chipAllView,
            "In Stock" to chipInStockView,
            "Low Stock" to chipLowStockView,
            "Critical" to chipCriticalView
        )

        chips.forEach { (filterName, chip) ->
            val isActive = activeFilter == filterName
            chip?.background = if (isActive) chipActiveBackground else chipInactiveBackground
            chip?.setTextColor(if (isActive) activeTextColor else inactiveTextColor)
        }
    }

    private fun loadProducts() {
        loadingView?.visibility = View.VISIBLE
        lifecycleScope.launch {
            val statusFilter = when (activeFilter) {
                "In Stock" -> "in_stock"
                "Low Stock" -> "all"
                "Critical" -> "critical"
                else -> "all"
            }
            allProducts = repository.getLocalProducts(searchQuery, statusFilter, sortOption).let { base ->
                if (activeFilter == "Low Stock") base.filter { it.stock < 10 } else base
            }
            adapter.updateList(allProducts)
            loadingView?.visibility = View.GONE
            emptyStateText?.visibility = if (allProducts.isEmpty()) View.VISIBLE else View.GONE
            renderDashboardSignals()
        }
    }

    private fun renderDashboardSignals() {
        lifecycleScope.launch {
            val lowCount = repository.getLowStockCount()
            val summaries = repository.getSalesSummaryPerProduct().take(3)

            lowStockBanner?.visibility = if (lowCount > 0) View.VISIBLE else View.GONE
            lowStockBannerText?.text = resources.getQuantityString(
                R.plurals.low_stock_banner,
                lowCount,
                lowCount
            )

            topProductsText?.text = if (summaries.isEmpty()) {
                getString(R.string.no_sales_summary)
            } else {
                summaries.joinToString(separator = "\n") {
                    "${it.productName}: ${it.totalSold} sold ($${String.format("%.2f", it.revenue)})"
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadProducts()
    }
}
