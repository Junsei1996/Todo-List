package com.example.todolist.ui.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.base.BaseBottomSheetFragment
import com.example.todolist.databinding.FragmentAddNewBinding
import com.example.todolist.model.ListParent
import com.example.todolist.util.Enums
import com.example.todolist.util.OnAddedListener
import com.example.todolist.viewModel.HomeViewModel

class FragmentAddNew() : BaseBottomSheetFragment() {

    private lateinit var addedListener: OnAddedListener;
    constructor(listener : OnAddedListener) : this(){
        this.addedListener = listener
    }

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
                showError(false)
                saveItem()
            }

        }
    }

    fun verifyInputs(title:String, desc:String, deadline:String):Boolean{
        return !(title.isNullOrEmpty())
//        return !(title.isNullOrEmpty() || desc.isNullOrEmpty() || deadline.isNullOrEmpty())
    }

    private fun saveItem() {

        var title: String = mBinding.etTitle.text.toString()
        var desc: String = mBinding.etDescription.text.toString()
        var deadline: String = mBinding.etDeadline.text.toString()

        if(verifyInputs(title, desc, deadline)){

            var item = ListParent(
                name = title,
                description = desc,
                deadline = deadline,
                status = Enums.STATUS.ACTIVE.name
            )

            viewModel.addItem(item).observe(this) {

                if(it == Enums.RESPONSE.SUCCESS){
                    addedListener.parentOrTaskAdded()
//                    findNavController().navigateUp()
                    dismiss()
                }

            }

        }else{
            showError(true)
        }

    }

    private fun showError(show:Boolean) {

        if(show){
            mBinding.tvError.visibility = View.VISIBLE
        }else{
            mBinding.tvError.visibility = View.GONE
        }

    }
}