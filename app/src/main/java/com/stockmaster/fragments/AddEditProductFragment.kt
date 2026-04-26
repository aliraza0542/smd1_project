package com.stockmaster.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.stockmaster.R
import com.stockmaster.database.DatabaseHelper
import com.stockmaster.database.dao.ProductDao
import com.stockmaster.models.Product
import kotlinx.coroutines.launch

class AddEditProductFragment : BottomSheetDialogFragment() {

    private var editProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editProduct = arguments?.getParcelable(ARG_PRODUCT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_edit_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etName = view.findViewById<EditText>(R.id.et_name)
        val etSku = view.findViewById<EditText>(R.id.et_sku)
        val spCategory = view.findViewById<Spinner>(R.id.spinner_category)
        val etPrice = view.findViewById<EditText>(R.id.et_price)
        val etStock = view.findViewById<EditText>(R.id.et_stock)
        val etDescription = view.findViewById<EditText>(R.id.et_description)
        val btnSave = view.findViewById<Button>(R.id.btn_save_product)

        val categories = listOf("Fabric", "Apparel", "Accessories", "Footwear", "Home Textile")
        spCategory.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categories)

        editProduct?.let { product ->
            etName.setText(product.name)
            etSku.setText(product.sku)
            etPrice.setText(product.price.toString())
            etStock.setText(product.stock.toString())
            etDescription.setText(product.description)
            val categoryIndex = categories.indexOf(product.category)
            if (categoryIndex >= 0) spCategory.setSelection(categoryIndex)
            btnSave.text = getString(R.string.update_product)
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val sku = etSku.text.toString().trim()
            val category = spCategory.selectedItem.toString()
            val price = etPrice.text.toString().toDoubleOrNull()
            val stock = etStock.text.toString().toIntOrNull()
            val description = etDescription.text.toString().trim()

            if (name.isBlank() || sku.isBlank() || price == null || stock == null) {
                Toast.makeText(requireContext(), R.string.product_form_validation, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val status = when {
                stock < 5 -> "critical"
                stock < 10 -> "low"
                else -> "in_stock"
            }

            val product = editProduct?.copy(
                name = name,
                sku = sku,
                category = category,
                price = price,
                stock = stock,
                status = status,
                description = description
            ) ?: Product(
                name = name,
                sku = sku,
                category = category,
                price = price,
                stock = stock,
                status = status,
                description = description
            )

            lifecycleScope.launch {
                val productDao = ProductDao(DatabaseHelper.getInstance(requireContext()))
                val success = if (editProduct == null) {
                    productDao.insertProduct(product) > 0
                } else {
                    productDao.updateProduct(product) > 0
                }

                if (success) {
                    parentFragmentManager.setFragmentResult(
                        REQUEST_KEY,
                        bundleOf(BUNDLE_REFRESH to true)
                    )
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), R.string.product_form_save_failed, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val REQUEST_KEY = "product_form_request"
        const val BUNDLE_REFRESH = "refresh"
        private const val ARG_PRODUCT = "arg_product"

        fun newInstance(product: Product? = null): AddEditProductFragment {
            return AddEditProductFragment().apply {
                arguments = bundleOf(ARG_PRODUCT to product)
            }
        }
    }
}

