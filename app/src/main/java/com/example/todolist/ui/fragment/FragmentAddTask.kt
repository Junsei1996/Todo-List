package com.example.todolist.ui.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentAddTaskBinding
import com.example.todolist.model.DetailItem
import com.example.todolist.util.Enums
import com.example.todolist.viewModel.HomeViewModel

class FragmentAddTask : BaseFragment() {

    private lateinit var mBinding: FragmentAddTaskBinding;
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory()
    }

    private var parentId : Int = -1

    override fun init() {

        arguments?.getInt(Enums.BUNDLE_KEYS.FILE_ID.name)?.let {
            parentId = it
        } ;

    }

    override fun getFragmentLayout(): Int = R.layout.fragment_add_task

    override fun getViewModel() {

    }

    override fun getViewBinding() {
        mBinding = binding as FragmentAddTaskBinding
    }

    override fun setListeners() {

        mBinding.apply {

            btnSave.setOnClickListener { saveTask() }
            btnCancel.setOnClickListener {
                findNavController().navigateUp()
            }


        }

    }

    private fun saveTask() {

        var taskTitle:String = mBinding.etTitle.text.toString()
        var item = DetailItem(parentId = parentId, name = taskTitle, status = Enums.STATUS.ACTIVE.name)
        viewModel.addTask(item).observe(this){
            findNavController().navigateUp()
        }

    }

}