package com.example.todolist.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<H : BaseViewHolder<M>, M> : RecyclerView.Adapter<H>() {

    var listItems: ArrayList<M> = arrayListOf()
        set(value) = run {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: ((M) -> Unit)? = null
    abstract var layout: (viewType: Int) -> Int

    abstract fun viewHolder(view: View, viewType: Int): H
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H =
        viewHolder(
            LayoutInflater.from(parent.context).inflate(layout(viewType), parent, false),
            viewType
        );

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.onBind(listItems[position], position);
        holder.itemView.setOnClickListener{
            itemClickListener?.let {
                it(listItems[position])
            }
        }
    }

    override fun getItemCount(): Int = listItems.size
    fun getList() = listItems
}