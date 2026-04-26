package com.stockmaster.network

import com.stockmaster.models.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
	@GET("products")
	suspend fun getProducts(@Query("limit") limit: Int = 30): ProductResponse
}


