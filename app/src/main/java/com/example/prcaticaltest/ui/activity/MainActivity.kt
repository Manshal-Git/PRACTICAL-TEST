package com.example.prcaticaltest.ui.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.prcaticaltest.R
import com.example.prcaticaltest.databinding.ActivityMainBinding
import com.example.prcaticaltest.ui.fragment.*
import com.example.prcaticaltest.utils.Constants
import com.example.prcaticaltest.utils.hide
import com.example.prcaticaltest.utils.show
import com.example.prcaticaltest.viewmodel.GalleryViewModel
import com.example.prcaticaltest.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var homeFragment: HomeFragment? = null
    private var galleryFragment: GalleryFragment? = null
    private var todoFragment: TodoFragment? = null
    private val homeViewModel by viewModel<HomeViewModel>()
    private val galleryViewModel by viewModel<GalleryViewModel>()
    private lateinit var windowInsetsController: WindowInsetsControllerCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        setContentView(binding.root)

        with(binding) {
            bottomNavBar.apply {
                setOnItemSelectedListener {
                    changeFragment(
                        when (it.itemId) {
                            R.id.home -> homeFragment
                                ?: HomeFragment.newInstance(homeViewModel) { id ->
                                    supportFragmentManager
                                        .beginTransaction()
                                        .addToBackStack(HomeFragment::class.java.name)
                                        .replace(R.id.frame, ProductDetailFragment().also {
                                            it.arguments = Bundle().apply {
                                                putInt(Constants.KEY_PRODUCT_ID, id)
                                            }
                                        })
                                        .commit()
                                    hideBottomNavBar()
                                }.apply {
                                    homeFragment = this
                                }
                            R.id.gallery -> galleryFragment ?: GalleryFragment.newInstance(
                                galleryViewModel
                            ) { url ->
                                supportFragmentManager
                                    .beginTransaction()
                                    .addToBackStack(GalleryFragment::class.java.name)
                                    .replace(R.id.frame, FullPhotoFragment().also {
                                        it.arguments = Bundle().apply {
                                            putString("url", url)
                                        }
                                    })
                                    .commit()
                                hideBottomNavBar()
                                makeFullScreen()
                            }.apply {
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

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                supportFragmentManager.apply {
                    if (backStackEntryCount == 0)
                        finish()
                    else
                        supportFragmentManager.popBackStack()
                }
                binding.bottomNavBar.apply {
                    if (!isVisible) {
                        show()
                        showStatusBar()
                    }
                }
            }
        })

    }

    private fun hideBottomNavBar() {
        binding.bottomNavBar.hide()
    }

    private fun changeFragment(fragment: Fragment? = null) {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.frame.id, fragment)
                .commit()
        }
    }

    private fun makeFullScreen() {
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }

    private fun showStatusBar() {
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
    }
}