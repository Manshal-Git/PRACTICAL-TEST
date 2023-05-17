package com.example.prcaticaltest.network

import com.example.prcaticaltest.model.Photo
import retrofit2.Call
import retrofit2.http.GET


interface PhotosNetworkCallInterface {
    @GET("photos")
    fun fetchPhotos(): Call<List<Photo>>
}
