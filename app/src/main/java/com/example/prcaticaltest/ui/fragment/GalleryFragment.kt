package com.example.prcaticaltest.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prcaticaltest.R
import com.example.prcaticaltest.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentGalleryBinding.bind(layoutInflater.inflate(R.layout.fragment_gallery,null,false))
        return binding.root
    }


    companion object{
        fun newInstance() = GalleryFragment().apply {

        }
    }
}