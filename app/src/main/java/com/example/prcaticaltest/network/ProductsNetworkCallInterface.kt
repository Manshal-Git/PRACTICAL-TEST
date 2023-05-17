package com.example.prcaticaltest.network

import com.example.prcaticaltest.model.Product
import com.example.prcaticaltest.model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductsNetworkCallInterface {

    @GET("products")
    fun fetchProducts(): Call<Products>

    @GET("products/{id}")
    fun fetchProductById(@Path("id") id: Int): Call<Product>
}
