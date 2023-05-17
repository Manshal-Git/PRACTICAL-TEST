package com.example.prcaticaltest.network

import android.util.Log
import com.example.prcaticaltest.model.Photo
import com.example.prcaticaltest.model.Product
import com.example.prcaticaltest.model.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(
    private val productsNetworkCallInterface: ProductsNetworkCallInterface,
    private val photosNetworkCallInterface: PhotosNetworkCallInterface
    ) {
    private val TAG = "Retrofit"

    fun getProducts(onComplete : (List<Product>?) -> Unit){
        productsNetworkCallInterface.fetchProducts()
            .enqueue(object : Callback<Products>{
                override fun onResponse(call: Call<Products>, response: Response<Products>) {
                    response.body()?.let {
                        onComplete(it.products)
                    }
                }

                override fun onFailure(call: Call<Products>, t: Throwable) {
                    Log.e(TAG, "Products : $t", )
                    onComplete(null)
                }
            })
    }


    fun getProductById(id : Int,onComplete : (Product?) -> Unit) {
        productsNetworkCallInterface.fetchProductById(id).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                response.body()?.let {
                    onComplete(it)
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.e(TAG, "Product by Id: $t", )
                onComplete(null)
            }
        })
    }

    fun getPhotos(onComplete : (List<Photo>?) -> Unit) {
        photosNetworkCallInterface.fetchPhotos().enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                response.body()?.let {
                    onComplete(it)
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.e(TAG, "Photos : $t", )
                onComplete(null)
            }
        })
    }

}