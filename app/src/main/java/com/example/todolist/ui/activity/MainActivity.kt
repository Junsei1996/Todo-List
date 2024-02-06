package com.example.todolist.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.todolist.R
import com.example.todolist.base.BaseActivity
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var mBinding: ActivityMainBinding;
    private lateinit var navController: NavController;
    override fun getActivityLayout(): Int = R.layout.activity_main

    override fun getViewBinding() {
        mBinding = binding as ActivityMainBinding

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.main_nav)

    }

    override fun setClickListeners() {

    }

    override fun onClick(p0: View?) {

    }

}