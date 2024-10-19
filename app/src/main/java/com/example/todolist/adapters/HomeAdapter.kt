package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.todolist.R
import com.example.todolist.base.BaseRecyclerViewAdapter
import com.example.todolist.base.BaseViewHolder
import com.example.todolist.databinding.ItemTodoMainBinding
import com.example.todolist.model.ListParent
import com.example.todolist.util.UtilityFunctions.isTimePassed

class HomeAdapter() : BaseRecyclerViewAdapter<HomeAdapter.ViewHolder, ListParent>() {

    inner class ViewHolder(var binding: ItemTodoMainBinding) :
        BaseViewHolder<ListParent>(binding.root) {
        override fun onBind(item: ListParent, position: Int) {

            binding.apply {

                txtTitle.text = item.name
                txtDescription.text = item.description ?: "N/A"
                txtCategory.text = item.categoryName.toString()
                txtDeadline.text = item.deadline ?: "N/A"

                var timePassed = isTimePassed(item.deadline)

                if(item.deadline.isNullOrEmpty()){
                    ivIndicator.backgroundTintList =
                        ContextCompat.getColorStateList(this.root.context, R.color.background)
                }
                else if (timePassed) {
                    ivIndicator.backgroundTintList =
                        ContextCompat.getColorStateList(this.root.context, R.color.chili_red)
                }else{
                    ivIndicator.backgroundTintList =
                        ContextCompat.getColorStateList(this.root.context, R.color.green)
                }

            }

        }

    }

    override var layout: (viewType: Int) -> Int
        get() = { R.layout.item_home }
        set(value) {}

    override fun viewHolder(view: View, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTodoMainBinding.inflate(
                LayoutInflater.from(view.context),
                view as ViewGroup,
                false
            )
        )
    }

}