package com.example.prcaticaltest.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prcaticaltest.R
import com.example.prcaticaltest.databinding.FragmentTodoBinding


class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoBinding.bind(layoutInflater.inflate(R.layout.fragment_todo, null, false))
        binding.toolbar.tvTitle.text = getString(R.string.todoCaps)
        return binding.root
    }

    companion object{
        fun newInstance() = TodoFragment().apply {

        }
    }
}