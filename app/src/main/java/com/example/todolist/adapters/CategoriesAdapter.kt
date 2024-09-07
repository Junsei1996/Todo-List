package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.example.todolist.R
import com.example.todolist.base.BaseRecyclerViewAdapter
import com.example.todolist.base.BaseViewHolder
import com.example.todolist.databinding.ItemAddCategoryBinding
import com.example.todolist.databinding.ItemCategoryBinding
import com.example.todolist.model.Category
import com.example.todolist.model.ListParent
import com.example.todolist.util.CategoryListener

class CategoriesAdapter() : BaseRecyclerViewAdapter<CategoriesAdapter.ViewHolder, Category>() {

    lateinit var catListener:CategoryListener;
    constructor(listener: CategoryListener) : this() {
        this.catListener = listener
    }

    abstract inner class ViewHolder(var binding: ViewDataBinding) :
        BaseViewHolder<Category>(binding.root) {
    }

    inner class CategoryViewHolder(var itemBinding: ItemCategoryBinding) :
        ViewHolder(itemBinding) {
        override fun onBind(item: Category, position: Int) {

            itemBinding.apply {

                tvCategory.text = item.title
                clCategory.setOnClickListener{
                    catListener.onCategorySelected(item.id)
                }
            }

        }

    }

    inner class AddCategoryViewHolder(var itemBinding: ItemAddCategoryBinding) :
        ViewHolder(itemBinding) {
        override fun onBind(item: Category, position: Int) {
            itemBinding.apply {
                btnAddCategory.setOnClickListener {
                    catListener.onAddCategory()
                }
            }
        }

    }

    override var layout: (viewType: Int) -> Int
        get() = {
            when (it) {

                1 -> {
                    R.layout.item_home
                }

                2 -> {
                    R.layout.item_add_category
                }

                else -> {
                    R.layout.item_home
                }
            }
        }
        set(value) {}

    override fun getItemViewType(position: Int): Int {
        return when (listItems[position].id) {

            0 -> {
                2
            }

            else -> {
                1
            }

        }
    }

    override fun viewHolder(view: View, viewType: Int): ViewHolder {

        if (viewType == 2) {
            return AddCategoryViewHolder(
                ItemAddCategoryBinding.inflate(
                    LayoutInflater.from(view.context),
                    view as ViewGroup,
                    false
                )
            )

        } else {

            return CategoryViewHolder(
                ItemCategoryBinding.inflate(
                    LayoutInflater.from(view.context),
                    view as ViewGroup,
                    false
                )
            )
        }
    }

}