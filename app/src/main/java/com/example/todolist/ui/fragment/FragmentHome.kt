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
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.model.Category
import com.example.todolist.model.ListParent
import com.example.todolist.util.AlertDialogListener
import com.example.todolist.util.CategoryListener
import com.example.todolist.util.Enums
import com.example.todolist.util.HomeCompleteListener
import com.example.todolist.util.OnAddedListener
import com.example.todolist.util.UtilityFunctions
import com.example.todolist.viewModel.HomeViewModel

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
                    deleteCategoryAlert(id)
                }

            })
            catAdapter.setSelectedPosition(1)
            rvCategory.adapter = catAdapter
            rvCategory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

//            catAdapter.listItems = getCategories()
//            getCategoriesfromDB()
            manageCategories()

            adapter = HomeAdapter()

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

//    private fun getCategories(): java.util.ArrayList<Category> {
//        return arrayListOf(Category(0,"Add","Dummy","Dummy"))
//    }

    private fun deleteCategoryAlert(categoryId:Int){
        UtilityFunctions.showAlertDialog(
            requireContext(),
            "Are you sure you want to delete the Category?",
            "All related tasks and sub tasks will also be deleted.",
            object : AlertDialogListener {
                override fun onAccept() {
                    deleteCategory(categoryId)
                }

                override fun onReject() {
                }
            })
    }
    private fun deleteCategory(categoryId:Int){
        viewModel.deleteCategory(categoryId).observe(this@FragmentHome){
            Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getFiles() {
        viewModel.getItems(Enums.STATUS.ACTIVE.name).observe(this@FragmentHome){
            if(!it.isNullOrEmpty()){
                showEmptyState(false)
                setListItems(it as ArrayList<ListParent>)
            }else{
                showEmptyState(true)
            }
        }
    }

    private fun getFilesbyCategory(catId:Int) {
        viewModel.getItemsByCategory(catId, Enums.STATUS.ACTIVE.name).observe(this@FragmentHome){
            if(!it.isNullOrEmpty()){
                showEmptyState(false)
                setListItems(it as ArrayList<ListParent>)
            }else{
                showEmptyState(true)
            }
        }
    }

//    var categoriesList : ArrayList<Category> = arrayListOf();
//    private fun getCategoriesfromDB() {
//        viewModel.getCategories().observe(this@FragmentHome){
//            if(!it.isNullOrEmpty()){
//                categoriesList.clear()
//                categoriesList.addAll(it)
//                setCategories(it as ArrayList<Category>)
//            }else{
//                setCategories(arrayListOf())
//            }
//        }
//    }

//    private fun setCategories(categories: java.util.ArrayList<Category>) {
//        var add = Category(0,"Add","Dummy","Dummy")
//        var cats = arrayListOf(add)
//        cats.addAll(categories)
//        catAdapter.listItems = cats
//    }

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

                var addNew = FragmentAddNew(listCategories,object:OnAddedListener{
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

    private var addCatItem = Category(id = -1, title = "Add")
    private var allCatItem = Category(id = -2, title = "ALL")
    private var listCategories = ArrayList<Category>()

    private fun manageCategories(){

        viewModel.getCategories().observe(this@FragmentHome){
            var tempArray = arrayListOf<Category>()
            tempArray.add(addCatItem)
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
