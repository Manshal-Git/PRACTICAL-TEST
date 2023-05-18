package com.example.prcaticaltest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.prcaticaltest.R
import com.example.prcaticaltest.databinding.ActivityProductDetailBinding
import com.example.prcaticaltest.network.Repository
import com.example.prcaticaltest.utils.Constants
import com.example.prcaticaltest.utils.hide
import com.example.prcaticaltest.utils.show
import org.koin.android.ext.android.get

class ProductDetailFragment : Fragment() {
    private lateinit var binding: ActivityProductDetailBinding
    private val repository : Repository = get()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)

        val productId = arguments?.getInt(Constants.KEY_PRODUCT_ID,0)

        if (productId != null) {
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
                        Glide.with(this@ProductDetailFragment)
                            .load(product.thumbnail)
                            .into(ivProduct)
                        tvProductTitle.text =  product.title
                        tvSubtitle.text = "From ${product.brand}"
                        tvDescription.text = product.description
                    }
                }
            }
        }

        binding.toolbar.apply {
            ivBack.apply {
                show()
                setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
            }
            tvTitle.text = getString(R.string.product_det)
        }

        return binding.root
    }

}