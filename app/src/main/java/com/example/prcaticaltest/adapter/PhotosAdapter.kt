package com.example.prcaticaltest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prcaticaltest.databinding.ItemPhotoBinding
import com.example.prcaticaltest.model.Photo

class PhotosAdapter(private val photos : List<Photo>, val onItemClick : (id : Int) -> Unit) : RecyclerView.Adapter<PhotosAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val photo = photos[position]
        val context = holder.itemView.context
        with(holder){
            Glide.with(context).load(photo.thumbnailUrl).into(ivProduct)
            card.setOnClickListener {
                onItemClick(photo.id)
            }
        }
    }


    override fun getItemCount() = photos.size


    inner class ProductViewHolder(binding : ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        val card = binding.root
        val ivProduct = binding.ivPhoto
    }
}