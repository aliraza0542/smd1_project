package com.stockmaster.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stockmaster.R
import com.stockmaster.adapters.InventoryAdapter
import com.stockmaster.models.Product

// F3 — RecyclerView in activity_inventory with InventoryAdapter
// F4 — Fragment Transaction target for bottom nav
// F5 — Search/Filter: filters RecyclerView items in real-time
class InventoryFragment : Fragment() {

    private lateinit var adapter: InventoryAdapter
    private var allProducts: List<Product> = Product.getSampleProducts()
    private var activeFilter: String = "All Items"

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
    }

    // F3 — RecyclerView setup with InventoryAdapter
    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_inventory)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

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
        val chipAll = view.findViewById<View>(R.id.chip_all)
        val chipLowStock = view.findViewById<View>(R.id.chip_low_stock)
        val chipElectronics = view.findViewById<View>(R.id.chip_electronics)

        chipAll?.setOnClickListener { activeFilter = "All Items"; filterProducts("") }
        chipLowStock?.setOnClickListener { activeFilter = "Low Stock"; filterProducts("") }
        chipElectronics?.setOnClickListener { activeFilter = "Electronics"; filterProducts("") }
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
