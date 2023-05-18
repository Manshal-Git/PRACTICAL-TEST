package com.example.prcaticaltest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.prcaticaltest.R
import com.example.prcaticaltest.databinding.ActivityProductDetailBinding
import com.example.prcaticaltest.network.Repository
import com.example.prcaticaltest.utils.Constants
import com.example.prcaticaltest.utils.hide
import com.example.prcaticaltest.utils.show
import org.koin.android.ext.android.get

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private val repository : Repository = get()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val productId = intent.getIntExtra(Constants.KEY_PRODUCT_ID,0)

        repository.getProductById(productId){ product ->
            if(product == null){
                binding.apply{
                    tvNoProducts.show()
                    detailsLayout.hide()
                }
            }else{
                binding.apply {
                    tvNoProducts.hide()
                    detailsLayout.show()
                    Glide.with(this@ProductDetailActivity)
                        .load(product.thumbnail)
                        .into(ivProduct)
                    tvProductTitle.text =  product.title
                    tvSubtitle.text = "From ${product.brand}"
                    tvDescription.text = product.description
                }
            }
        }

        binding.toolbar.apply {
            ivBack.apply {
                show()
                setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            }
            tvTitle.text = getString(R.string.product_det)
        }
    }

}