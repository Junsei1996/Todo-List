package com.example.todolist.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.example.todolist.R
import com.example.todolist.adapters.CategoriesAdapter
import com.example.todolist.adapters.HomeAdapter
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.model.Category
import com.example.todolist.model.ListParent
import com.example.todolist.util.CategoryListener
import com.example.todolist.util.Enums
import com.example.todolist.util.HomeCompleteListener
import com.example.todolist.util.OnAddedListener
import com.example.todolist.viewModel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class FragmentHome : BaseFragment() {

    lateinit var mBinding: FragmentHomeBinding;
    lateinit var adapter: HomeAdapter
    lateinit var catAdapter: CategoriesAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory()
    }

    override fun init() {

        mBinding.apply {

            catAdapter = CategoriesAdapter(object :CategoryListener{
                override fun onCategorySelected(id: Int) {
                    Toast.makeText(requireContext(), id.toString(), Toast.LENGTH_SHORT).show()
                    getFilesbyCategory(id)
                }

                override fun onAddCategory() {
                    findNavController().navigate(R.id.action_fragmentHome_to_fragmentAddCategory)
                }

            })

            rvCategory.adapter = catAdapter
            rvCategory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

//            catAdapter.listItems = getCategories()
            getCategoriesfromDB()

            adapter = HomeAdapter(object: HomeCompleteListener{
                override fun onComplete(item: ListParent) {
                    updateFile(item, Enums.STATUS.COMPLETED.name)
                }

                override fun onUncheck(item: ListParent) {
                    updateFile(item, Enums.STATUS.ACTIVE.name)
                }

            });

            rvHome.adapter = adapter
            rvHome.layoutManager = LinearLayoutManager(requireContext())

            adapter.itemClickListener = {
                var bundle = Bundle();
                bundle.putInt(Enums.BUNDLE_KEYS.FILE_ID.name,it.id)
                bundle.putParcelable(Enums.BUNDLE_KEYS.PARENT_FILE.name, it)
                findNavController().navigate(R.id.action_fragmentHome_to_fragmentDetails, bundle)
            }

        }

        getFiles()

    }

    private fun getCategories(): java.util.ArrayList<Category> {
        return arrayListOf(Category(0,"Add","Dummy","Dummy"))
    }

    private fun getFiles() {
        viewModel.getItems().observe(this@FragmentHome){
            if(!it.isNullOrEmpty()){
                showEmptyState(false)
                setListItems(it as ArrayList<ListParent>)
            }else{
                showEmptyState(true)
            }
        }
    }

    private fun getFilesbyCategory(catId:Int) {
        viewModel.getItemsByCategory(catId).observe(this@FragmentHome){
            if(!it.isNullOrEmpty()){
                showEmptyState(false)
                setListItems(it as ArrayList<ListParent>)
            }else{
                showEmptyState(true)
            }
        }
    }

    var categoriesList : ArrayList<Category> = arrayListOf();
    private fun getCategoriesfromDB() {
        viewModel.getCategories().observe(this@FragmentHome){
            if(!it.isNullOrEmpty()){
                categoriesList.clear()
                categoriesList.addAll(it)
                setCategories(it as ArrayList<Category>)
            }else{
                setCategories(arrayListOf())
            }
        }
    }

    private fun setCategories(categories: java.util.ArrayList<Category>) {
        var add = Category(0,"Add","Dummy","Dummy")
        var cats = arrayListOf(add)
        cats.addAll(categories)
        catAdapter.listItems = cats
    }

    private fun updateFile(item: ListParent, status: String) {

        viewModel.updateFile(item.id, status).observe(this){
            getFiles()
        }

    }

    override fun getFragmentLayout(): Int = R.layout.fragment_home

    override fun getViewModel() {

    }
    override fun getViewBinding() {
        mBinding = binding as FragmentHomeBinding
    }
    override fun setListeners() {

        mBinding.apply {

            btnAdd.setOnClickListener {
//                findNavController().navigate(R.id.action_fragmentHome_to_fragmentAddNew)

                var addNew = FragmentAddNew(categoriesList,object:OnAddedListener{
                    override fun parentOrTaskAdded() {
                        getFiles()
                    }
                })
                addNew.show(childFragmentManager, "ADD_NEW_TASK")
            }

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

}