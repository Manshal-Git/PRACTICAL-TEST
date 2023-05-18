package com.example.prcaticaltest.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prcaticaltest.model.Product
import com.example.prcaticaltest.network.Repository

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products
    private fun setProducts(value : List<Product>){
        _products.postValue(value)
    }

    private val _scrollPosition = MutableLiveData<Parcelable>()
    val scrollPosition: LiveData<Parcelable> get() = _scrollPosition
    fun setScrollPosition(value : Parcelable){
        _scrollPosition.postValue(value)
    }

    fun getProducts(){
        repository.getProducts {
            setProducts(it?: emptyList())
        }
    }


}