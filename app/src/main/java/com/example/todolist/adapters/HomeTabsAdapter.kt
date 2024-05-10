package com.example.todolist.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todolist.ui.fragment.FragmentHistory
import com.example.todolist.ui.fragment.FragmentHome


class HomeTabsAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        when(position){
            0->{
                val fragment = FragmentHome()
                return fragment
            }
            1->{
                val fragment = FragmentHistory()
                return fragment
            }
            else -> {
                val fragment = FragmentHome()
                return fragment
            }
        }

    }

}