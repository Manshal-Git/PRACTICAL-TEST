package com.example.prcaticaltest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prcaticaltest.databinding.ItemProductBinding
import com.example.prcaticaltest.model.Product

class ProductsAdapter(private val products : List<Product>,val onItemClick : (id : Int) -> Unit) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        val context = holder.itemView.context
        with(holder){
            Glide.with(context).load(product.thumbnail).into(ivProduct)
            tvTitle.text = product.title
            tvSubtitle.text = product.description
            card.setOnClickListener {
                onItemClick(product.id)
            }
        }
    }


    override fun getItemCount() = products.size


    inner class ProductViewHolder(binding : ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val card = binding.root
        val ivProduct = binding.ivProduct
        val tvTitle = binding.tvTitle
        val tvSubtitle = binding.tvSubtitle
    }
}