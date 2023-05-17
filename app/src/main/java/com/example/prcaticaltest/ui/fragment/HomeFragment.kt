package com.example.prcaticaltest.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prcaticaltest.R
import com.example.prcaticaltest.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.bind(layoutInflater.inflate(R.layout.fragment_home,null,false))
        return binding.root
    }

    companion object {
        fun newInstance() = HomeFragment().apply {

        }
    }
}