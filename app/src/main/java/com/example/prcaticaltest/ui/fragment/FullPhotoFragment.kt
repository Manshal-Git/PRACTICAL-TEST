package com.example.prcaticaltest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.prcaticaltest.R
import com.example.prcaticaltest.databinding.ActivityFullPhotoBinding

class FullPhotoFragment : Fragment() {
    private lateinit var binding: ActivityFullPhotoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityFullPhotoBinding.bind(
            layoutInflater.inflate(
                R.layout.activity_full_photo,
                null,
                false
            )
        )
        binding.apply {
            arguments?.getString("url")?.let {

                Glide.with(requireActivity()).load(it)
                    .placeholder(CircularProgressDrawable(requireContext()).apply {
                        start()
                        centerRadius = 48f
                        strokeWidth = 8f
                    })
                    .into(ivFullPicture)
            }
        }
        return binding.root
    }
}