package com.example.todolist.ui.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.base.BaseBottomSheetFragment
import com.example.todolist.databinding.FragmentAddCategoryBinding
import com.example.todolist.databinding.FragmentAddNewBinding
import com.example.todolist.model.Category
import com.example.todolist.util.Enums
import com.example.todolist.viewModel.HomeViewModel

class FragmentAddCategory : BaseBottomSheetFragment() {

    private lateinit var mBinding: FragmentAddCategoryBinding;
    override fun getViewModel() {

    }

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory()
    }

    override fun init() {

    }

    override fun getFragmentLayout(): Int = R.layout.fragment_add_category

    override fun getViewBinding() {
        mBinding = binding as FragmentAddCategoryBinding
    }

    override fun setListeners() {
        mBinding.apply {

            btnCancel.setOnClickListener {
                findNavController().navigateUp()
            }

            btnSave.setOnClickListener {
                showError(false)
                saveCategory(getSavedCategory())
            }

        }
    }

    private fun getSavedCategory(): Category {
        mBinding.apply {
            return Category(0,
            etTitle.text.toString(),
            )
        }
    }

    private fun showError(showError: Boolean) {

    }

    private fun saveCategory(cat: Category) {

        viewModel.addCategory(cat).observe(this@FragmentAddCategory) {
            if (it == Enums.RESPONSE.SUCCESS) {
                findNavController().navigateUp()
                dismiss()
            }
        }
    }
}