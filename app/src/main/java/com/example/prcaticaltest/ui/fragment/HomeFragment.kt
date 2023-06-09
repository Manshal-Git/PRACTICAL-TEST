package com.example.prcaticaltest.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prcaticaltest.R
import com.example.prcaticaltest.adapter.ProductsAdapter
import com.example.prcaticaltest.databinding.FragmentHomeBinding
import com.example.prcaticaltest.utils.hide
import com.example.prcaticaltest.utils.show
import com.example.prcaticaltest.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var vm: HomeViewModel
    private lateinit var showProductDetails : (Int) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentHomeBinding.bind(layoutInflater.inflate(R.layout.fragment_home, null, false))
        binding.apply {
            toolbar.tvTitle.text = getString(R.string.productlist)
            rvProducts.layoutManager = LinearLayoutManager(requireContext())
        }
        vm.apply {
            if (products.value == null) {
                binding.loadingScreen.root.show()
                binding.contentScreen.hide()
                getProducts()
            }
            products.observe(viewLifecycleOwner) { it ->
                if (it.isEmpty()) {
                    binding.apply {
                        rvProducts.hide()
                        tvNoProducts.show()
                    }
                } else {
                    binding.tvNoProducts.hide()
                    binding.rvProducts.apply {
                        show()
                        adapter = ProductsAdapter(it) {
                            showProductDetails(it)
                        }
                        layoutManager?.onRestoreInstanceState(scrollPosition.value)
                    }
                    binding.loadingScreen.root.hide()
                    binding.contentScreen.show()
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val scrollState = binding.rvProducts.layoutManager?.onSaveInstanceState()
        scrollState?.let { vm.setScrollPosition(it) }
    }

    companion object {
        fun newInstance(homeViewModel: HomeViewModel,navigate : (Int) -> Unit) = HomeFragment().apply {
            vm = homeViewModel
            showProductDetails = navigate
        }
    }
}