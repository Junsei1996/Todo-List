package com.example.todolist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.todolist.database.DataRepository
import com.example.todolist.model.DetailItem
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

    fun addTask(task : DetailItem) = liveData {
        val result = DataRepository.addNewTask(task)
        emit(result)
    }

    fun getTasks(fileId:Int) = liveData {
        val result = DataRepository.getTasks(fileId)
        emit(result)
    }

    fun deleteTask(taskId:Int) = liveData {
        val result = DataRepository.deleteTask(taskId)
        emit(result)
    }

    fun updateTask(taskId:Int, status:String) = liveData {
        val result = DataRepository.updateTask(taskId, status)
        emit(result)
    }

    fun updateFile(fileId:Int, status:String) = liveData {
        val result = DataRepository.updateFile(fileId, status)
        emit(result)
    }

    fun updateAllTasks(fileId:Int, status:String) = liveData {
        val result = DataRepository.updateAllTasks(fileId, status)
        emit(result)
    }

    class HomeViewModelFactory():ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel() as T
        }

    }

}