package com.stockmaster.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stockmaster.R
import com.stockmaster.adapters.CatalogAdapter
import com.stockmaster.database.DatabaseHelper
import com.stockmaster.database.dao.ProductDao
import com.stockmaster.database.dao.SaleDao
import com.stockmaster.models.CatalogProduct
import com.stockmaster.models.Product
import com.stockmaster.network.ApiClient
import com.stockmaster.repository.ProductRepository
import kotlinx.coroutines.launch

class BrowseCatalogFragment : Fragment() {

    private lateinit var adapter: CatalogAdapter
    private lateinit var repository: ProductRepository

    private var progressBar: ProgressBar? = null
    private var errorCard: View? = null
    private var emptyState: View? = null
    private var recyclerView: RecyclerView? = null
    private var errorMessage: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_browse_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHelper = DatabaseHelper.getInstance(requireContext())
        repository = ProductRepository(
            productDao = ProductDao(dbHelper),
            saleDao = SaleDao(dbHelper),
            apiService = ApiClient.apiService
        )

        progressBar = view.findViewById(R.id.catalog_progress)
        errorCard = view.findViewById(R.id.catalog_error_card)
        emptyState = view.findViewById(R.id.catalog_empty_state)
        recyclerView = view.findViewById(R.id.recycler_catalog)
        errorMessage = view.findViewById(R.id.tv_catalog_error)

        adapter = CatalogAdapter(emptyList()) { catalogProduct ->
            showImportDialog(catalogProduct)
        }

        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = adapter

        view.findViewById<View>(R.id.btn_retry_catalog)?.setOnClickListener {
            loadCatalogFromApi()
        }

        loadCatalogFromApi()
    }

    private fun loadCatalogFromApi() {
        showLoading(true)
        lifecycleScope.launch {
            runCatching { repository.getApiProducts(30) }
                .onSuccess { products ->
                    adapter.updateList(products)
                    showLoading(false)
                    recyclerView?.visibility = if (products.isEmpty()) View.GONE else View.VISIBLE
                    emptyState?.visibility = if (products.isEmpty()) View.VISIBLE else View.GONE
                    errorCard?.visibility = View.GONE
                }
                .onFailure {
                    showLoading(false)
                    recyclerView?.visibility = View.GONE
                    emptyState?.visibility = View.GONE
                    errorCard?.visibility = View.VISIBLE
                    errorMessage?.text = getString(R.string.catalog_error)
                }
        }
    }

    private fun showImportDialog(catalogProduct: CatalogProduct) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_import_catalog, null)
        val etPrice = dialogView.findViewById<EditText>(R.id.et_import_price)
        val etStock = dialogView.findViewById<EditText>(R.id.et_import_stock)

        etPrice.setText(catalogProduct.price.toString())
        etStock.setText(catalogProduct.stock.toString())

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.import_inventory_title))
            .setView(dialogView)
            .setNegativeButton(android.R.string.cancel, null)
            .setPositiveButton(R.string.import_inventory_cta) { _, _ ->
                val price = etPrice.text.toString().toDoubleOrNull()
                val stock = etStock.text.toString().toIntOrNull()
                if (price == null || stock == null) {
                    Toast.makeText(requireContext(), R.string.product_form_validation, Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                lifecycleScope.launch {
                    val status = when {
                        stock < 5 -> "critical"
                        stock < 10 -> "low"
                        else -> "in_stock"
                    }
                    val product = Product(
                        name = catalogProduct.title,
                        sku = "CAT-${catalogProduct.id}",
                        category = catalogProduct.category,
                        price = price,
                        stock = stock,
                        status = status,
                        description = catalogProduct.description,
                        thumbnailUrl = catalogProduct.thumbnail
                    )
                    val inserted = repository.addProduct(product)
                    if (inserted > 0) {
                        Toast.makeText(requireContext(), R.string.import_inventory_success, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), R.string.product_form_save_failed, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .show()
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading) {
            recyclerView?.visibility = View.GONE
            emptyState?.visibility = View.GONE
            errorCard?.visibility = View.GONE
        }
    }
}

