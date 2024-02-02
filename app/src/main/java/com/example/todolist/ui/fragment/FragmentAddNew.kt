package com.example.todolist.ui.fragment

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentAddNewBinding

class FragmentAddNew : BaseFragment() {

    private lateinit var mBinding: FragmentAddNewBinding;

    override fun init() {

        mBinding.apply {

            etTitle.text
            etDescription.text
            etDeadline.text

        }

    }

    override fun getFragmentLayout(): Int = R.layout.fragment_add_new

    override fun getViewModel() {

    }

    override fun getViewBinding() {
        mBinding = binding as FragmentAddNewBinding
    }

    override fun setListeners() {

        mBinding.apply {

            btnCancel.setOnClickListener {
                findNavController().navigateUp()
            }

            btnSave.setOnClickListener {
                saveItem()
                findNavController().navigateUp()
            }

        }
    }

    private fun saveItem() {
        Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
    }
}