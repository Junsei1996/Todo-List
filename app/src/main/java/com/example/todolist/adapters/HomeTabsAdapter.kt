package com.example.todolist.adapters

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todolist.ui.fragment.FragmentHistory
import com.example.todolist.ui.fragment.FragmentHistoryParent
import com.example.todolist.ui.fragment.FragmentHome
import com.example.todolist.ui.fragment.FragmentHomeParent


class HomeTabsAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        when(position){
            0->{
                val fragment = FragmentHomeParent()
                return fragment
            }
            1->{
                val fragment = FragmentHistoryParent()
                return fragment
            }
            else -> {
                val fragment = FragmentHomeParent()
                return fragment
            }
        }

    }

}