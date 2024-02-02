package com.example.todolist.ui.fragment

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.adapters.DetailsAdapter
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentDetailsBinding
import com.example.todolist.model.DetailItem
import com.example.todolist.model.ListParent
import com.example.todolist.util.Enums

class FragmentDetails : BaseFragment() {

    private lateinit var mBinding: FragmentDetailsBinding
    private lateinit var detailAdapter : DetailsAdapter

    override fun init() {

        mBinding.apply {

            detailAdapter = DetailsAdapter()
            detailAdapter.listItems = getData()
            rvItems.adapter = detailAdapter
            rvItems.layoutManager = LinearLayoutManager(requireContext())

        }

    }

    override fun getFragmentLayout(): Int = R.layout.fragment_details

    override fun getViewModel() {

    }

    override fun getViewBinding() {
        mBinding = binding as FragmentDetailsBinding
    }

    override fun setListeners() {
        mBinding.apply {
            btnComplete.setOnClickListener {  }
            btnDelete.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun getData(): ArrayList<DetailItem> {
        var homeList = arrayListOf<DetailItem>()

        homeList.add(
            DetailItem(
                1,
                "Home Tasks",
                Enums.STATUS.ACTIVE.name
            )
        )
        homeList.add(
            DetailItem(
                2,
                "Office Tasks",
                Enums.STATUS.ACTIVE.name
            )
        )
        homeList.add(
            DetailItem(
                3,
                "Friends Tasks",
                Enums.STATUS.ACTIVE.name
            )
        )
        homeList.add(
            DetailItem(
                4,
                "Learning Tasks",
                Enums.STATUS.ACTIVE.name
            )
        )
        return homeList;
    }

}