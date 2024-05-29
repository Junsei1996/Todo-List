package com.example.todolist.ui.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.todolist.R
import com.example.todolist.adapters.HomeTabsAdapter
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeParentBinding
import com.example.todolist.databinding.FragmentParentBinding
import com.example.todolist.viewModel.HomeViewModel

class FragmentHomeParent : BaseFragment() {

    lateinit var mBinding: FragmentHomeParentBinding;
    private lateinit var navController: NavController;

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory()
    }

    override fun init() {

        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.main_nav)

    }

    override fun getFragmentLayout(): Int = R.layout.fragment_home_parent

    override fun getViewModel() {

    }

    override fun getViewBinding() {
        mBinding = binding as FragmentHomeParentBinding
    }

    override fun setListeners() {

    }
}