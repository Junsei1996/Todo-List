package com.example.todolist.ui.activity

import android.view.View
import com.example.todolist.R
import com.example.todolist.adapters.HomeTabsAdapter
import com.example.todolist.base.BaseActivity
import com.example.todolist.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : BaseActivity() {

    lateinit var mBinding: ActivityMainBinding;
    lateinit var adapter: HomeTabsAdapter
    override fun getActivityLayout(): Int = R.layout.activity_main

    override fun getViewBinding() {
        mBinding = binding as ActivityMainBinding

        adapter = HomeTabsAdapter(this)
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

}