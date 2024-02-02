package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolist.model.ListParent
import com.example.todolist.R
import com.example.todolist.base.BaseRecyclerViewAdapter
import com.example.todolist.base.BaseViewHolder
import com.example.todolist.databinding.ItemHomeBinding

class HomeAdapter() : BaseRecyclerViewAdapter<HomeAdapter.ViewHolder, ListParent>(){

    inner class ViewHolder(var binding: ItemHomeBinding):BaseViewHolder<ListParent>(binding.root){
        override fun onBind(item: ListParent, position: Int) {

        }

    }

    override var layout: (viewType: Int) -> Int
        get() = { R.layout.item_home}
        set(value) {}

    override fun viewHolder(view: View, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeBinding.inflate(
            LayoutInflater.from(view.context),
            view as ViewGroup,
            false
        ))
    }

}