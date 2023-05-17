package com.example.prcaticaltest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.prcaticaltest.databinding.ActivityFullPhotoBinding

class FullPhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}