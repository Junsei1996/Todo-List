package com.example.todolist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.todolist.model.ListParent
import com.example.todolist.R
import com.example.todolist.base.BaseRecyclerViewAdapter
import com.example.todolist.base.BaseViewHolder
import com.example.todolist.databinding.ItemHomeBinding
import com.example.todolist.util.Enums
import com.example.todolist.util.HomeCompleteListener

class HomeAdapter() : BaseRecyclerViewAdapter<HomeAdapter.ViewHolder, ListParent>() {

    private lateinit var completeListener: HomeCompleteListener;

    constructor(completedListener: HomeCompleteListener) : this() {
        this.completeListener = completedListener;
    }

    inner class ViewHolder(var binding: ItemHomeBinding) :
        BaseViewHolder<ListParent>(binding.root) {
        override fun onBind(item: ListParent, position: Int) {

            binding.apply {

//                txtSerial.text = (position + 1).toString()
                cbComplete.setOnCheckedChangeListener(null)
                txtTitle.text = item.name
                txtDescription.text = item.description

                if (item.status == Enums.STATUS.COMPLETED.name) {
                    container.setCardBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.green
                        )
                    )
                    cbComplete.isChecked = true
                } else {
                    container.setCardBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    cbComplete.isChecked = false
                }
                cbComplete.setOnCheckedChangeListener { compoundButton, checked ->
                    if (checked) {
                        completeListener.onComplete(item)
                        Toast.makeText(binding.root.context, "Checked", Toast.LENGTH_SHORT).show()
                    } else {
                        completeListener.onUncheck(item)
                        Toast.makeText(binding.root.context, "Un_Checked", Toast.LENGTH_SHORT).show()
                    }
                }


            }

        }

    }

    override var layout: (viewType: Int) -> Int
        get() = { R.layout.item_home }
        set(value) {}

    override fun viewHolder(view: View, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(view.context),
                view as ViewGroup,
                false
            )
        )
    }

}