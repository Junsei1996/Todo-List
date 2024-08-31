package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolist.R
import com.example.todolist.base.BaseRecyclerViewAdapter
import com.example.todolist.base.BaseViewHolder
import com.example.todolist.databinding.ItemCategoryBinding
import com.example.todolist.model.Category

class CategoriesAdapter : BaseRecyclerViewAdapter<CategoriesAdapter.ViewHolder, Category>() {

    inner class ViewHolder(var binding: ItemCategoryBinding) :
        BaseViewHolder<Category>(binding.root) {
        override fun onBind(item: Category, position: Int) {

            binding.apply {

                tvCategory.text = item.title

            }

        }

    }

    override var layout: (viewType: Int) -> Int
        get() = { R.layout.item_home }
        set(value) {}

    override fun viewHolder(view: View, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(view.context),
                view as ViewGroup,
                false
            )
        )
    }

}