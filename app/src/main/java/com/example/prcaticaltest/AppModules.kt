package com.example.prcaticaltest

import com.example.prcaticaltest.network.PhotosNetworkCallInterface
import com.example.prcaticaltest.network.ProductsNetworkCallInterface
import com.example.prcaticaltest.network.Repository
import com.example.prcaticaltest.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {


    single(named("retrofitProducts")) {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("retrofitPhotos")) {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ProductsNetworkCallInterface> {
        get<Retrofit>(named("retrofitProducts")).create(ProductsNetworkCallInterface::class.java)
    }

    single<PhotosNetworkCallInterface> {
        get<Retrofit>(named("retrofitPhotos")).create(PhotosNetworkCallInterface::class.java)
    }

    single {
        Repository(get(),get())
    }


    viewModel {
        HomeViewModel(get())
    }
}