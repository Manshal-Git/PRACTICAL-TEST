package com.example.prcaticaltest.network

import com.example.prcaticaltest.model.Photo
import com.example.prcaticaltest.model.Product
import com.example.prcaticaltest.model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface NetworkCallsInterface {

    @GET("https://dummyjson.com/products")
    fun fetchProducts(): Call<Products>

    @GET("https://dummyjson.com/products/{id}")
    fun fetchProductById(@Path("id") id: Int): Call<Product>

    @GET("https://jsonplaceholder.typicode.com/photos")
    fun fetchPhotos(): Call<Photo>
}
