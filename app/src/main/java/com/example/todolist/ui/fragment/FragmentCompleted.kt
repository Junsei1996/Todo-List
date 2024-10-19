package com.example.todolist.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.adapters.CategoriesAdapter
import com.example.todolist.adapters.HomeAdapter
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentCompletedBinding
import com.example.todolist.model.Category
import com.example.todolist.model.ListParent
import com.example.todolist.util.CategoryListener
import com.example.todolist.util.Enums
import com.example.todolist.util.HomeCompleteListener
import com.example.todolist.viewModel.HomeViewModel

class FragmentCompleted  : BaseFragment() {

    lateinit var mBinding: FragmentCompletedBinding;
    lateinit var adapter: HomeAdapter
    lateinit var catAdapter: CategoriesAdapter

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory()
    }

    override fun init() {

        mBinding.apply {

            catAdapter = CategoriesAdapter(object : CategoryListener {
                override fun onCategorySelected(id: Int) {
                    Toast.makeText(requireContext(), id.toString(), Toast.LENGTH_SHORT).show()
                    if(id == -2){
                        getFiles()
                    }else{
                        getFilesbyCategory(id)
                    }
                }

                override fun onAddCategory() {
                    findNavController().navigate(R.id.action_fragmentHome_to_fragmentAddCategory)
                }

                override fun onRemoveCategory(id: Int) {

                }

            })

            rvCategory.adapter = catAdapter
            rvCategory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

            adapter = HomeAdapter();
            rvHome.adapter = adapter
            rvHome.layoutManager = LinearLayoutManager(requireContext())

            adapter.itemClickListener = {
                var bundle = Bundle();
                bundle.putInt(Enums.BUNDLE_KEYS.FILE_ID.name,it.id)
                bundle.putParcelable(Enums.BUNDLE_KEYS.PARENT_FILE.name, it)
                findNavController().navigate(R.id.action_completedFragment_to_fragmentDetails, bundle)
            }

        }
        manageCategories()
        getFiles()

    }

    override fun getFragmentLayout(): Int = R.layout.fragment_completed

    override fun getViewModel() {

    }

    override fun getViewBinding() {
        mBinding = binding as FragmentCompletedBinding
    }

    override fun setListeners() {

    }

    private fun getFiles() {
        viewModel.getItems(Enums.STATUS.COMPLETED.name).observe(this@FragmentCompleted){
            if(!it.isNullOrEmpty()){
                showEmptyState(false)
                setListItems(it as ArrayList<ListParent>)
            }else{
                showEmptyState(true)
            }
        }
    }

    private fun getFilesbyCategory(catId:Int) {
        viewModel.getItemsByCategory(catId, Enums.STATUS.COMPLETED.name).observe(this@FragmentCompleted){
            if(!it.isNullOrEmpty()){
                showEmptyState(false)
                setListItems(it as ArrayList<ListParent>)
            }else{
                showEmptyState(true)
            }
        }
    }

    private fun updateFile(item: ListParent, status: String) {

        viewModel.updateFile(item.id, status).observe(this){
            getFiles()
        }

    }

    private fun setListItems(arrayList: ArrayList<ListParent>) {

        adapter.listItems = arrayList
        adapter.notifyDataSetChanged()

    }

    private fun showEmptyState(show: Boolean){

        if(show){
            mBinding.clEmptyState.visibility = View.VISIBLE
            mBinding.rvHome.visibility = View.GONE
        }else{
            mBinding.clEmptyState.visibility = View.GONE
            mBinding.rvHome.visibility = View.VISIBLE
        }

    }

    private var allCatItem = Category(id = -2, title = "ALL")
    private var listCategories = ArrayList<Category>()
    private fun manageCategories(){

        viewModel.getCategories().observe(this@FragmentCompleted){
            var tempArray = arrayListOf<Category>()
            tempArray.add(allCatItem)

            if(!it.isNullOrEmpty()){
                listCategories.clear()
                listCategories.addAll(it as ArrayList<Category>)
                tempArray.addAll(listCategories)
            }
            catAdapter.listItems = tempArray
        }

    }

}