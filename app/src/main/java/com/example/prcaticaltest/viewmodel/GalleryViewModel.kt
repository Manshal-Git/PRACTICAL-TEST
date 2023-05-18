package com.example.prcaticaltest.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prcaticaltest.model.Photo
import com.example.prcaticaltest.network.Repository

class GalleryViewModel(private val repository: Repository) : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos
    private fun setphotos(value : List<Photo>){
        _photos.postValue(value)
    }

    private val _scrollPosition = MutableLiveData<Parcelable>()
    val scrollPosition: LiveData<Parcelable> get() = _scrollPosition

    fun setScrollPosition(value : Parcelable){
        _scrollPosition.postValue(value)
    }

    fun getPhotos(){
        repository.getPhotos {
            setphotos(it?: emptyList())
        }
    }

}