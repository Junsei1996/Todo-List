package com.example.todolist.ui.fragment

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.adapters.HomeAdapter
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.model.ListParent
import com.example.todolist.util.Enums
import java.util.ArrayList

class FragmentHome : BaseFragment() {

    lateinit var mBinding: FragmentHomeBinding;
    lateinit var adapter: HomeAdapter
    override fun init() {

        mBinding.apply {

            adapter = HomeAdapter();
            adapter.listItems = getListItems();
            rvHome.adapter = adapter
            rvHome.layoutManager = LinearLayoutManager(requireContext())

            adapter.itemClickListener = {
                findNavController().navigate(R.id.action_fragmentHome_to_fragmentDetails)
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
    private fun getListItems(): ArrayList<ListParent> {

        var homeList = arrayListOf<ListParent>()

        homeList.add(
            ListParent(
                1,
                "Home Tasks",
                "List of tasks you have to do at home",
                Enums.STATUS.ACTIVE
            )
        )
        homeList.add(
            ListParent(
                2,
                "Office Tasks",
                "List of tasks you have to do at office",
                Enums.STATUS.ACTIVE
            )
        )
        homeList.add(
            ListParent(
                3,
                "Friends Tasks",
                "List of tasks you have to do with friends",
                Enums.STATUS.ACTIVE
            )
        )
        homeList.add(
            ListParent(
                4,
                "Learning Tasks",
                "List of tasks you have to do to Learn",
                Enums.STATUS.ACTIVE
            )
        )
        return homeList;

    }
}