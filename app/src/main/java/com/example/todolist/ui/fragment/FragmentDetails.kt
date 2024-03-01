package com.example.todolist.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.adapters.DetailsAdapter
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentDetailsBinding
import com.example.todolist.model.DetailItem
import com.example.todolist.model.ListParent
import com.example.todolist.util.AlertDialogListener
import com.example.todolist.util.Enums
import com.example.todolist.util.OnAddedListener
import com.example.todolist.util.TaskListener
import com.example.todolist.util.UtilityFunctions
import com.example.todolist.viewModel.HomeViewModel

class FragmentDetails : BaseFragment() {

    private lateinit var mBinding: FragmentDetailsBinding
    private lateinit var detailAdapter : DetailsAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory()
    }

    private var parentId : Int = -1
    private lateinit var parent: ListParent;

    override fun init() {

//        arguments?.getInt(Enums.BUNDLE_KEYS.FILE_ID.name)?.let {
//            parentId = it
//        } ;
        arguments?.getParcelable<ListParent>(Enums.BUNDLE_KEYS.PARENT_FILE.name)?.let{
            parent = it
            parentId = parent.id
        }

        mBinding.apply {

            tvTitle.text = parent.name
            tvDescription.text = parent.description
            tvDeadline.text = parent.deadline

            detailAdapter = DetailsAdapter(object: TaskListener{
                override fun onComplete(item: DetailItem) {
                    updateTask(item, Enums.STATUS.COMPLETED.name)
                }

                override fun onUncheck(item: DetailItem) {
                    updateTask(item, Enums.STATUS.ACTIVE.name)
                }

                override fun onDelete(item: DetailItem) {
                    deleteTask(item)
                }

            })
            detailAdapter.listItems = getData()
            rvItems.adapter = detailAdapter
            rvItems.layoutManager = LinearLayoutManager(requireContext())

        }

        getTasks()

    }

    private fun getTasks() {
        viewModel.getTasks(parentId).observe(this){
            if(!it.isNullOrEmpty()){
                showEmptyState(false)
                setListItems(it)
            }else{
                showEmptyState(true)
            }
        }
    }

    private fun updateTask(item: DetailItem, status: String) {

        viewModel.updateTask(item.id, status).observe(this) {
            getTasks()
        }

    }

    override fun getFragmentLayout(): Int = R.layout.fragment_details

    override fun getViewModel() {

    }

    override fun getViewBinding() {
        mBinding = binding as FragmentDetailsBinding
    }

    override fun setListeners() {
        mBinding.apply {
            btnComplete.setOnClickListener {
                viewModel.updateFile(parentId, Enums.STATUS.COMPLETED.name).observe(this@FragmentDetails){
                    findNavController().navigateUp()
                }
            }
            btnDelete.setOnClickListener {
                deleteItem()
            }
            btnAddTask.setOnClickListener {
                var bundle = Bundle()
                bundle.putInt(Enums.BUNDLE_KEYS.FILE_ID.name, parentId)
//                findNavController().navigate(R.id.action_fragmentDetails_to_fragmentAddTask, bundle)

                var addNew = FragmentAddTask(parentId, object: OnAddedListener {
                    override fun parentOrTaskAdded() {
                        getTasks()
                    }
                })
                addNew.show(childFragmentManager, "ADD_NEW_TASK")

            }
        }
    }

    private fun getData(): ArrayList<DetailItem> {
        var homeList = arrayListOf<DetailItem>()
        return homeList;
    }

    private fun deleteItem(){
        UtilityFunctions.showAlertDialog(requireContext(), "Are you sure you want to delete the File?","All tasks liked to file will be deleted.", object:AlertDialogListener{
            override fun onAccept() {
                viewModel.deleteItem(parentId).observe(this@FragmentDetails){
                    findNavController().navigateUp()
                }
            }

            override fun onReject() {
            }
        })
    }

    private fun deleteTask(item: DetailItem) {
        UtilityFunctions.showAlertDialog(requireContext(), "Are you sure you want to delete the task?","Once deleted the task can not be recovered.", object:AlertDialogListener{
            override fun onAccept() {
                viewModel.deleteTask(item.id).observe(this@FragmentDetails){
                    getTasks()
                }
            }

            override fun onReject() {
            }
        })
    }

    private fun setListItems(arrayList: ArrayList<DetailItem>) {

        detailAdapter.listItems = arrayList
        detailAdapter.notifyDataSetChanged()

    }

    private fun showEmptyState(show: Boolean){

        if(show){
            mBinding.clEmptyState.visibility = View.VISIBLE
            mBinding.rvItems.visibility = View.GONE
        }else{
            mBinding.clEmptyState.visibility = View.GONE
            mBinding.rvItems.visibility = View.VISIBLE
        }

    }

}