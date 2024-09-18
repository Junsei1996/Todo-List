package com.example.todolist.ui.fragment

import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.adapters.CategoriesAdapter
import com.example.todolist.adapters.CategoryListAdapter
import com.example.todolist.base.BaseBottomSheetFragment
import com.example.todolist.databinding.FragmentAddNewBinding
import com.example.todolist.model.Category
import com.example.todolist.model.ListParent
import com.example.todolist.ui.dialog.DatePickerFragment
import com.example.todolist.ui.dialog.TimePickerFragment
import com.example.todolist.util.DateAndTimeListener
import com.example.todolist.util.Enums
import com.example.todolist.util.OnAddedListener
import com.example.todolist.viewModel.HomeViewModel

class FragmentAddNew() : BaseBottomSheetFragment() {

    private lateinit var addedListener: OnAddedListener;
    private lateinit var categoriesAdapter: CategoryListAdapter;
    private lateinit var categories:ArrayList<Category>

    constructor(listener : OnAddedListener) : this(){
        this.addedListener = listener
    }
    constructor(categories:ArrayList<Category>, listener : OnAddedListener) : this(){
        this.categories = categories
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

            categoriesAdapter = CategoryListAdapter(requireContext(),0,  categories);

            catSpinner.adapter = categoriesAdapter

            cbDeadlineDate.setOnCheckedChangeListener(object:CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    if(isChecked){
                        cbDeadlineText.isChecked = false
                        handleDateTimeVisibility(!isChecked)
                    }
                }
            })

            cbDeadlineText.setOnCheckedChangeListener(object:CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    if(isChecked){
                        cbDeadlineDate.isChecked = false
                        handleDateTimeVisibility(isChecked)
                    }
                }
            })

            tvDate.setOnClickListener(object:View.OnClickListener{
                override fun onClick(v: View?) {
                    DatePickerFragment(object:DateAndTimeListener{
                        override fun onDateSelected(date: String) {
                            tvDate.text = date
                        }

                        override fun onTimeSelected(time: String) {
                        }

                    }).show(childFragmentManager, "DatePicker")
                }
            })

            tvTime.setOnClickListener(object:View.OnClickListener{
                override fun onClick(v: View?) {
                    TimePickerFragment(object:DateAndTimeListener{
                        override fun onDateSelected(date: String) {
                        }

                        override fun onTimeSelected(time: String) {
                            tvTime.text = time
                        }

                    }).show(childFragmentManager, "timePicker")
                }
            })
            cbDeadlineDate.isChecked = true;
        }

    }

    fun handleDateTimeVisibility(textSelected:Boolean){

        mBinding.clDate.isVisible = !textSelected
        mBinding.etDeadline.isVisible = textSelected

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
//                Toast.makeText(requireContext(),(catSpinner.selectedItem as Category).title, Toast.LENGTH_LONG).show()
            }

            btnSave.setOnClickListener {
                showError(false)
                saveItem()
            }

        }
    }

    fun verifyInputs(title:String?, category: Category?, desc:String, deadline:String):Boolean{
        return !(title.isNullOrEmpty() || category?.id == null )
//        return !(title.isNullOrEmpty())
//        return !(title.isNullOrEmpty() || desc.isNullOrEmpty() || deadline.isNullOrEmpty())
    }

    private fun saveItem() {

        var title: String = mBinding.etTitle.text.toString()
        var desc: String = mBinding.etDescription.text.toString()
        var category = mBinding.catSpinner.selectedItem
        var deadline: String = if(mBinding.cbDeadlineDate.isChecked){
            mBinding.tvDate.text.toString() + " " + mBinding.tvTime.text.toString()
        }else{
            mBinding.etDeadline.text.toString()
        }

        if(verifyInputs(title,category as Category?, desc, deadline)){

            Toast.makeText(requireContext(), category?.id.toString(), Toast.LENGTH_SHORT).show()

            var item = ListParent(
                name = title,
                description = desc,
                categoryId = category.id,
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