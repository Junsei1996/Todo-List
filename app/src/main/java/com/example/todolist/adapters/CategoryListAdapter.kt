package com.example.todolist.adapters

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.todolist.R
import com.example.todolist.model.Category

class CategoryListAdapter(context: Context, resource: Int, categories: ArrayList<Category>) :
    ArrayAdapter<Category>(context, resource, categories) {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_category_dropdown, parent, false)
        } else {
            view = convertView
        }

        getItem(position)?.let { setItemForCountry(view, it) }

        return view

    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        if (position == 0) {
            view = layoutInflater.inflate(R.layout.item_category_dropdown, parent, false)
            view.setOnClickListener {
                val root = parent.rootView
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK))
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK))
            }
        } else {
            view = layoutInflater.inflate(R.layout.item_category_dropdown, parent, false)
            getItem(position)?.let { setItemForCountry(view, it) }
        }
        return view

    }

    override fun getItem(position: Int): Category? {
        if (position == 0) {
            return null
        }
        return super.getItem(position - 1)
    }

    override fun getCount() = super.getCount() + 1

    override fun isEnabled(position: Int) = position != 0

    private fun setItemForCountry(view: View, category:Category) {

        var tvTitle = view.findViewById<TextView>(R.id.tv_category)
        tvTitle.text = category.title

    }

}