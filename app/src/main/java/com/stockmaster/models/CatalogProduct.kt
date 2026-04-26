package com.stockmaster.models

data class ProductResponse(
    val products: List<CatalogProduct>
)

data class CatalogProduct(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val stock: Int,
    val thumbnail: String
)

