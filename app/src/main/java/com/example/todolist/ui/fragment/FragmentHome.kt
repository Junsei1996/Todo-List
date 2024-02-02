package com.example.todolist.ui.fragment

import android.widget.Toast
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeBinding

class FragmentHome: BaseFragment() {

    lateinit var mBinding: FragmentHomeBinding;

    override fun init() {

        Toast.makeText(requireContext(), "Here", Toast.LENGTH_SHORT).show()

    }

    override fun getFragmentLayout(): Int = R.layout.fragment_home

    override fun getViewModel() {

    }

    override fun getViewBinding() {
        mBinding = binding as FragmentHomeBinding
    }

    override fun setListeners() {

    }
}