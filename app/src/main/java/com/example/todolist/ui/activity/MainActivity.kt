package com.example.todolist.ui.activity

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todolist.R
import com.example.todolist.adapters.HomeTabsAdapter
import com.example.todolist.base.BaseActivity
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.util.BackPressedListener
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : BaseActivity() {

    lateinit var mBinding: ActivityMainBinding;
    lateinit var adapter: HomeTabsAdapter

    lateinit var historyNavController: NavController
    lateinit var homeNavController: NavController

    override fun getActivityLayout(): Int = R.layout.activity_main

    override fun getViewBinding() {
        mBinding = binding as ActivityMainBinding

        adapter = HomeTabsAdapter(this, object : BackPressedListener{
            override fun setHistoryNavController(navController: NavController) {
                historyNavController = navController
            }

            override fun setHomeNavController(navController: NavController) {
                homeNavController = navController
            }

        })
        mBinding.viewPager.adapter = adapter;

        TabLayoutMediator(mBinding.tabLayout,mBinding.viewPager ){
            tab, pos ->
            when(pos){
                0 -> {
                    tab.text= "Home"
                    tab.icon = getDrawable(R.drawable.home)
                }
                1 ->{
                    tab.text = "History"
                    tab.icon = getDrawable(R.drawable.archive)
                }
            }

        }.attach()

    }

    override fun setClickListeners() {

    }

    override fun onClick(p0: View?) {

    }

    override fun onBackPressed() {
        val activeFragment = getActiveFragment()

        val navHost = activeFragment?.childFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController
        if (navController.currentBackStackEntry != null) {
            // If the back stack is not empty, pop the back stack
            navController.popBackStack()
        }
        else {
            super.onBackPressed()
        }
    }

    private fun getActiveFragment(): Fragment? {
        val adapter = mBinding.viewPager.adapter as FragmentStateAdapter
        if (adapter != null) {
            val currentItem: Int = mBinding.viewPager.currentItem
            val tag = "f$currentItem"
            return supportFragmentManager.findFragmentByTag(tag)
        }
        return null
    }


}