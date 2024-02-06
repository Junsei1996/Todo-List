package com.example.todolist.ui.fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentAddNewBinding
import com.example.todolist.model.ListParent
import com.example.todolist.util.Enums
import com.example.todolist.viewModel.HomeViewModel

class FragmentAddNew : BaseFragment() {

    private lateinit var mBinding: FragmentAddNewBinding;
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory()
    }

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
            }

        }
    }

    private fun saveItem() {

        var title: String = mBinding.etTitle.text.toString()
        var desc: String = mBinding.etDescription.text.toString()
        var deadline: String = mBinding.etDeadline.text.toString()

        var item = ListParent(
            name = title,
            description = desc,
            deadline = deadline,
            status = Enums.STATUS.ACTIVE.name
        )

        viewModel.addItem(item).observe(this) {

            if(it == Enums.RESPONSE.SUCCESS){
                Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }

        }

    }
}