package com.example.prcaticaltest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.prcaticaltest.R
import com.example.prcaticaltest.databinding.ActivityMainBinding
import com.example.prcaticaltest.ui.fragment.GalleryFragment
import com.example.prcaticaltest.ui.fragment.HomeFragment
import com.example.prcaticaltest.ui.fragment.TodoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var homeFragment: HomeFragment? = null
    private var galleryFragment: GalleryFragment? = null
    private var todoFragment: TodoFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            bottomNavBar.apply {
                setOnItemSelectedListener {
                    changeFragment(
                        when (it.itemId) {
                            R.id.home -> homeFragment ?: HomeFragment.newInstance().apply {
                                homeFragment = this
                            }
                            R.id.gallery -> galleryFragment ?: GalleryFragment.newInstance().apply {
                                galleryFragment = this
                            }
                            R.id.todo -> todoFragment ?: TodoFragment.newInstance().apply {
                                todoFragment = this
                            }
                            else -> null
                        }
                    )
                    true
                }
                selectedItemId = R.id.home
            }
        }

    }

    private fun changeFragment(fragment: Fragment? = null) {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.frame.id,fragment)
                .commit()
        }
    }
}