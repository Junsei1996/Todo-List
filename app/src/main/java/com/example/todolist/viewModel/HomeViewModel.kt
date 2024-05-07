package com.example.todolist.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.DataRepository
import com.example.todolist.model.DetailItem
import com.example.todolist.model.ListParent
import com.example.todolist.util.Enums
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    fun getItems() = liveData {
//        viewModelScope.launch {
            DataRepository.getItems()
//                .flowOn(Dispatchers.IO)
                .catch { e ->
                Log.d("", e.localizedMessage)
                }
                .collect {
                    emit (it.single())
                }
//        }
    }

    fun addItem(item: ListParent) = liveData {
        val result = DataRepository.addNewItem(item)
        emit(Enums.RESPONSE.SUCCESS)
    }

    fun deleteItem(id: Int) = liveData {
        val result = DataRepository.deleteItem(id)
        emit(result)
    }

    fun addTask(task: DetailItem) = liveData {
        val result = DataRepository.addNewTask(task)
        emit(result)
    }

    fun getTasks(fileId: Int) = liveData {
        val result = DataRepository.getTasks(fileId)
        emit(result)
    }

    fun deleteTask(taskId: Int) = liveData {
        val result = DataRepository.deleteTask(taskId)
        emit(result)
    }

    fun updateTask(taskId: Int, status: String) = liveData {
        val result = DataRepository.updateTask(taskId, status)
        emit(result)
    }

    fun updateFile(fileId: Int, status: String) = liveData {
        val result = DataRepository.updateFile(fileId, status)
        emit(result)
    }

    fun updateAllTasks(fileId: Int, status: String) = liveData {
        val result = DataRepository.updateAllTasks(fileId, status)
        emit(result)
    }

    class HomeViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel() as T
        }

    }

}