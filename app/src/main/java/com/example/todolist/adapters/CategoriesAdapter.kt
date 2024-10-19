package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
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
    var selectedPos = 0
    constructor(listener: CategoryListener) : this() {
        this.catListener = listener
    }

    abstract inner class ViewHolder(var binding: ViewDataBinding) :
        BaseViewHolder<Category>(binding.root) {
    }

    fun setSelectedPosition(pos:Int){
        selectedPos = pos
        notifyItemChanged(selectedPos)
    }

    inner class CategoryViewHolder(var itemBinding: ItemCategoryBinding) :
        ViewHolder(itemBinding) {
        override fun onBind(item: Category, position: Int) {

            itemBinding.apply {

                tvCategory.text = item.title

                if(selectedPos == position){
                    cvCategory.setCardBackgroundColor(ContextCompat.getColor(itemBinding.root.context, R.color.green))
                }else{
                    cvCategory.setCardBackgroundColor(ContextCompat.getColor(itemBinding.root.context, R.color.white))
                }

                clCategory.setOnClickListener{
                    catListener.onCategorySelected(item.id)

                    val previousSelectedPosition = selectedPos
                    selectedPos = position
                    // Notify the adapter to refresh the old and new selected positions
                    notifyItemChanged(previousSelectedPosition)
                    notifyItemChanged(selectedPos)
                }
                clCategory.setOnLongClickListener {
                    if(item.id != -2){
                        catListener.onRemoveCategory(item.id)
                    }
                    true
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

            -1 -> {
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