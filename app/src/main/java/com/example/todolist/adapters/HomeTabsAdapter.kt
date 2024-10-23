package com.example.todolist.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todolist.ui.fragment.FragmentHistoryParent
import com.example.todolist.ui.fragment.FragmentHomeParent
import com.example.todolist.util.BackPressedListener


class HomeTabsAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    lateinit var backListener: BackPressedListener
    constructor(fragment: FragmentActivity, backListener: BackPressedListener):this(fragment){
        this.backListener = backListener
    }

    override fun createFragment(position: Int): Fragment {

        when(position){
            0->{
                val fragment = FragmentHomeParent(backListener)
                return fragment
            }
            1->{
                val fragment = FragmentHistoryParent(backListener)
                return fragment
            }
            else -> {
                val fragment = FragmentHomeParent(backListener)
                return fragment
            }
        }

    }

}