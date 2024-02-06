package com.example.todolist.ui.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.adapters.HomeAdapter
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.model.ListParent
import com.example.todolist.util.Enums
import com.example.todolist.util.HomeCompleteListener
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

            adapter = HomeAdapter(object: HomeCompleteListener{
                override fun onComplete(item: ListParent) {
                    updateFile(item, Enums.STATUS.COMPLETED.name)
                }

                override fun onUncheck(item: ListParent) {
                    updateFile(item, Enums.STATUS.ACTIVE.name)
                }

            });
            rvHome.adapter = adapter
            rvHome.layoutManager = LinearLayoutManager(requireContext())

            adapter.itemClickListener = {
                var bundle = Bundle();
                bundle.putInt(Enums.BUNDLE_KEYS.FILE_ID.name,it.id)
                bundle.putParcelable(Enums.BUNDLE_KEYS.PARENT_FILE.name, it)
                findNavController().navigate(R.id.action_fragmentHome_to_fragmentDetails, bundle)
            }

        }

        getFiles()

    }

    private fun getFiles() {
        viewModel.getItems().observe(this){
            if(!it.isNullOrEmpty()){
                setListItems(it)
            }
        }
    }

    private fun updateFile(item: ListParent, status: String) {

        viewModel.updateFile(item.id, status).observe(this){
            getFiles()
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