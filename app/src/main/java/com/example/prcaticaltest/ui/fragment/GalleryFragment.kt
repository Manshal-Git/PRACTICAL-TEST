package com.example.prcaticaltest.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.prcaticaltest.R
import com.example.prcaticaltest.adapter.PhotosAdapter
import com.example.prcaticaltest.databinding.FragmentGalleryBinding
import com.example.prcaticaltest.utils.hide
import com.example.prcaticaltest.utils.show
import com.example.prcaticaltest.viewmodel.GalleryViewModel

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private lateinit var vm: GalleryViewModel
    private lateinit var makeFullScreen : (String) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.bind(layoutInflater.inflate(R.layout.fragment_gallery, null, false))
        binding.apply {
            toolbar.tvTitle.text = getString(R.string.gallery)
            rvPhotos.layoutManager = GridLayoutManager(requireContext(), 3)
        }
        vm.apply {
            if (photos.value == null) {
                binding.contentScreen.hide()
                binding.loadingScreen.root.show()
                getPhotos()
            }
            photos.observe(viewLifecycleOwner) { items ->
                if (items.isEmpty()) {
                    binding.apply {
                        rvPhotos.hide()
                        tvNoPhotos.show()
                    }
                } else {
                    binding.tvNoPhotos.hide()
                    binding.rvPhotos.apply {
                        show()
                        adapter = PhotosAdapter(items) { url ->
                            makeFullScreen(url)
                        }
                    }
                    binding.contentScreen.show()
                    binding.loadingScreen.root.hide()
                }
            }
        }
        return binding.root
    }

    companion object {
        fun newInstance(galleryViewModel: GalleryViewModel,navigate : (String) -> Unit) = GalleryFragment().apply {
            vm = galleryViewModel
            makeFullScreen = navigate
        }
    }
}
