package com.example.todolist.ui.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHistoryParentBinding
import com.example.todolist.util.BackPressedListener
import com.example.todolist.viewModel.HomeViewModel

class FragmentHistoryParent() : BaseFragment() {

    constructor(backListener: BackPressedListener):this(){
        this.backListener = backListener
    }

    lateinit var backListener: BackPressedListener
    lateinit var mBinding: FragmentHistoryParentBinding;
    private lateinit var navController: NavController;
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory()
    }

    override fun init() {

        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.history_nav)
        backListener.setHomeNavController(navController)
    }

    override fun getFragmentLayout(): Int = R.layout.fragment_history_parent

    override fun getViewModel() {

    }

    override fun getViewBinding() {
        mBinding = binding as FragmentHistoryParentBinding
    }

    override fun setListeners() {

    }
}