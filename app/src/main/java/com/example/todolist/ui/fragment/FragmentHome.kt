package com.example.todolist.ui.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.adapters.HomeAdapter
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.model.ListParent
import com.example.todolist.viewModel.HomeViewModel
import kotlin.collections.ArrayList

class FragmentHome : BaseFragment() {

    lateinit var mBinding: FragmentHomeBinding;
    lateinit var adapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory()
    }


    override fun init() {

        mBinding.apply {

            adapter = HomeAdapter();
            rvHome.adapter = adapter
            rvHome.layoutManager = LinearLayoutManager(requireContext())

            adapter.itemClickListener = {
                findNavController().navigate(R.id.action_fragmentHome_to_fragmentDetails)
            }

        }

        viewModel.getItems().observe(this){
            if(!it.isNullOrEmpty()){
                setListItems(it)
            }
        }

    }
    override fun getFragmentLayout(): Int = R.layout.fragment_home

    override fun getViewModel() {

    }
    override fun getViewBinding() {
        mBinding = binding as FragmentHomeBinding
    }
    override fun setListeners() {

        mBinding.apply {

            btnAdd.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentHome_to_fragmentAddNew)
            }

        }

    }
    private fun setListItems(arrayList: ArrayList<ListParent>) {

        adapter.listItems = arrayList
        adapter.notifyDataSetChanged()

    }
}