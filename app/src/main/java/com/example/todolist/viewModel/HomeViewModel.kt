package com.example.todolist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.todolist.database.DataRepository
import com.example.todolist.model.ListParent
import com.example.todolist.util.Enums

class HomeViewModel(): ViewModel() {

    fun getItems() = liveData {
        val result = DataRepository.getItems()
        emit(result)
    }

    fun addItem(item: ListParent) = liveData {
        val result = DataRepository.addNewItem(item)
        emit(Enums.RESPONSE.SUCCESS)
    }

    fun deleteItem(id:Int) = liveData {
        val result = DataRepository.deleteItem(id)
        emit(result)
    }

    class HomeViewModelFactory():ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel() as T
        }

    }

}