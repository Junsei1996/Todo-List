package com.example.todolist.ui.fragment

import androidx.fragment.app.viewModels
import com.example.todolist.R
import com.example.todolist.adapters.HomeTabsAdapter
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHistoryBinding
import com.example.todolist.databinding.FragmentParentBinding
import com.example.todolist.viewModel.HomeViewModel

class FragmentParent : BaseFragment() {


    lateinit var mBinding: FragmentParentBinding;
    lateinit var adapter:HomeTabsAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory()
    }

    override fun init() {

        adapter = HomeTabsAdapter(this)
        mBinding.viewPager.adapter = adapter;

    }

    override fun getFragmentLayout(): Int = R.layout.fragment_parent

    override fun getViewModel() {

    }

    override fun getViewBinding() {
        mBinding = binding as FragmentParentBinding
    }

    override fun setListeners() {

    }
}