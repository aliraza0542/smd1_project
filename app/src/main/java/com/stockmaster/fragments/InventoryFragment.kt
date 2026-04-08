package com.stockmaster.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.stockmaster.R
import com.stockmaster.adapters.ProductAdapter
import com.stockmaster.viewmodels.ProductViewModel

class InventoryFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter

    private lateinit var tvProductCount: TextView
    private lateinit var etSearch: EditText
    private lateinit var chipGroup: ChipGroup
    private lateinit var tvInStockCount: TextView
    private lateinit var tvLowStockCount: TextView
    private lateinit var tvOutOfStockCount: TextView
    private lateinit var rvProducts: RecyclerView
    private lateinit var layoutEmptyProducts: LinearLayout

    private var selectedCategory = "All"
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
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        // Bind views
        tvProductCount = view.findViewById(R.id.tv_product_count)
        etSearch = view.findViewById(R.id.et_search)
        chipGroup = view.findViewById(R.id.chip_group_categories)
        tvInStockCount = view.findViewById(R.id.tv_in_stock_count)
        tvLowStockCount = view.findViewById(R.id.tv_low_stock_count)
        tvOutOfStockCount = view.findViewById(R.id.tv_out_of_stock_count)
        rvProducts = view.findViewById(R.id.rv_products)
        layoutEmptyProducts = view.findViewById(R.id.layout_empty_products)

        // Setup RecyclerView
        productAdapter = ProductAdapter(requireContext())
        rvProducts.layoutManager = LinearLayoutManager(requireContext())
        rvProducts.adapter = productAdapter

        // Observe products
        viewModel.allProducts.observe(viewLifecycleOwner) { products ->
            productAdapter.setData(products)
            updateEmptyState()
        }

        // Product count
        viewModel.totalProductCount.observe(viewLifecycleOwner) { count ->
            tvProductCount.text = "${count ?: 0} Products"
        }

        // Summary chip counts
        viewModel.inStockCount.observe(viewLifecycleOwner) { count ->
            tvInStockCount.text = "${count ?: 0} In Stock"
        }

        viewModel.lowStockCount.observe(viewLifecycleOwner) { count ->
            tvLowStockCount.text = "${count ?: 0} Low Stock"
        }

        viewModel.outOfStockCount.observe(viewLifecycleOwner) { count ->
            tvOutOfStockCount.text = "${count ?: 0} Out of Stock"
        }

        // F5 — Search TextWatcher
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                productAdapter.filter(s.toString(), selectedCategory)
                updateEmptyState()
            }
        })

        // F5 — Category Chip filter
        chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isEmpty()) {
                selectedCategory = "All"
            } else {
                val chipId = checkedIds[0]
                val chip = view.findViewById<Chip>(chipId)
                selectedCategory = chip?.text?.toString() ?: "All"
            }
            productAdapter.filter(etSearch.text.toString(), selectedCategory)
            updateEmptyState()
        }
    }

    private fun updateEmptyState() {
        if (productAdapter.getFilteredList().isEmpty()) {
            rvProducts.visibility = View.GONE
            layoutEmptyProducts.visibility = View.VISIBLE
        } else {
            rvProducts.visibility = View.VISIBLE
            layoutEmptyProducts.visibility = View.GONE
        }
    }
}
