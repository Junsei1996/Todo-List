package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolist.R
import com.example.todolist.base.BaseRecyclerViewAdapter
import com.example.todolist.base.BaseViewHolder
import com.example.todolist.databinding.ItemDetailBinding
import com.example.todolist.model.DetailItem

class DetailsAdapter : BaseRecyclerViewAdapter<DetailsAdapter.ViewHolder, DetailItem>() {

    override var layout: (viewType: Int) -> Int
        get() = { R.layout.item_detail }
        set(value) {}

    override fun viewHolder(view: View, viewType: Int): ViewHolder {
        return ViewHolder(ItemDetailBinding.inflate(
            LayoutInflater.from(view.context),
            view as ViewGroup,
            false
        ))
    }

    inner class ViewHolder(var binding: ItemDetailBinding) : BaseViewHolder<DetailItem>(binding.root) {
        override fun onBind(item: DetailItem, position: Int) {

            binding.apply {

                txtSerial.text = (position+1).toString()
                txtTitle.text = listItems[position].name

            }

        }

    }




}