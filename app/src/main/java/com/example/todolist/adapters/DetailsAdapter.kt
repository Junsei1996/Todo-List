package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.todolist.R
import com.example.todolist.base.BaseRecyclerViewAdapter
import com.example.todolist.base.BaseViewHolder
import com.example.todolist.databinding.ItemSwipeDetailBinding
import com.example.todolist.model.DetailItem
import com.example.todolist.util.Enums
import com.example.todolist.util.TaskListener

class DetailsAdapter() : BaseRecyclerViewAdapter<DetailsAdapter.ViewHolder, DetailItem>() {

    private lateinit var taskListener: TaskListener;

    constructor(completedListener: TaskListener) : this() {
        this.taskListener = completedListener;
    }

    override var layout: (viewType: Int) -> Int
        get() = { R.layout.item_swipe_detail }
        set(value) {}

    override fun viewHolder(view: View, viewType: Int): ViewHolder {
        return ViewHolder(ItemSwipeDetailBinding.inflate(
            LayoutInflater.from(view.context),
            view as ViewGroup,
            false
        ))
    }

    inner class ViewHolder(var binding: ItemSwipeDetailBinding) : BaseViewHolder<DetailItem>(binding.root) {
        override fun onBind(item: DetailItem, position: Int) {

            binding.apply {

                cbComplete.setOnCheckedChangeListener(null)
                txtTitle.text = listItems[position].name

                if (item.status == Enums.STATUS.COMPLETED.name) {
                    container.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.green
                        )
                    )
                    cbComplete.isChecked = true
                }else{
                    container.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    cbComplete.isChecked = false
                }

                btnDeleteItem.setOnClickListener {
                    taskListener.onDelete(item)
                }

                cbComplete.setOnCheckedChangeListener { compoundButton, checked ->
                    if (checked) {
                        taskListener.onComplete(item)
                    } else {
                        taskListener.onUncheck(item)
                    }
                }

            }

        }

    }




}