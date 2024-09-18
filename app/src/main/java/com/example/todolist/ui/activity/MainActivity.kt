package com.example.todolist.ui.activity

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.todolist.R
import com.example.todolist.base.BaseActivity
import com.example.todolist.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {

    lateinit var mBinding: ActivityMainBinding;

    lateinit var navController: NavController

    override fun getActivityLayout(): Int = R.layout.activity_main

    override fun getViewBinding() {
        mBinding = binding as ActivityMainBinding

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fHomeFrag) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.main_nav)


    }

    override fun setClickListeners() {
        mBinding.tabLayout.setOnItemSelectedListener {

            navController.setGraph(
                when (it.itemId) {
                    R.id.iHome -> R.navigation.main_nav
                    R.id.iCompleted -> R.navigation.completed_nav
                    R.id.iArchived -> R.navigation.history_nav
                    else -> R.navigation.main_nav
                }
            )

            return@setOnItemSelectedListener true
        }
    }

    override fun onClick(p0: View?) {

    }





}