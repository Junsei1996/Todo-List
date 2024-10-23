package com.example.todolist.ui.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.base.BaseBottomSheetFragment
import com.example.todolist.databinding.FragmentAddTaskBinding
import com.example.todolist.model.DetailItem
import com.example.todolist.util.Enums
import com.example.todolist.util.OnAddedListener
import com.example.todolist.viewModel.HomeViewModel

class FragmentAddTask() : BaseBottomSheetFragment() {

    private lateinit var addedListener: OnAddedListener;
    private var parentId: Int = -1
    constructor(listener : OnAddedListener) : this(){
        this.addedListener = listener
    }

    constructor(parentId:Int, listener: OnAddedListener):this(){
        this.parentId = parentId
        this.addedListener = listener
    }

    private lateinit var mBinding: FragmentAddTaskBinding;
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory()
    }



    override fun init() {

        arguments?.getInt(Enums.BUNDLE_KEYS.FILE_ID.name)?.let {
            parentId = it
        };

    }

    override fun getFragmentLayout(): Int = R.layout.fragment_add_task

    override fun getViewModel() {

    }

    override fun getViewBinding() {
        mBinding = binding as FragmentAddTaskBinding
    }

    override fun setListeners() {

        mBinding.apply {

            btnSave.setOnClickListener {
                showError(false)
                saveTask()
            }
            btnCancel.setOnClickListener {
                dismiss()
            }


        }

    }

    private fun saveTask() {

        var taskTitle: String = mBinding.etTitle.text.toString()

        if (!taskTitle.isNullOrEmpty()) {
            var item =
                DetailItem(parentId = parentId, name = taskTitle, status = Enums.STATUS.ACTIVE.name)
            viewModel.addTask(item).observe(this) {
//                findNavController().navigateUp()
                addedListener.parentOrTaskAdded()
                dismiss();
            }
        } else {
            showError(true)
        }


    }

    private fun showError(show: Boolean) {

        if (show) {
            mBinding.tvError.visibility = View.VISIBLE
        } else {
            mBinding.tvError.visibility = View.GONE
        }

    }

}