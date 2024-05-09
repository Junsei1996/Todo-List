package com.example.todolist.database

import com.example.todolist.TodoApplication
import com.example.todolist.model.DetailItem
import com.example.todolist.model.ListParent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

object DataRepository {

    fun addNewItem(item: ListParent) {
        CoroutineScope(Dispatchers.IO).launch {
            TodoApplication.database.filesDao().insertFile(item)
        }
    }

    fun getItems(): Flow<MutableList<ListParent>> {
        return TodoApplication.database.filesDao().getFiles()
    }

    fun deleteItem(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            TodoApplication.database.filesDao().deleteFile(id)
        }
    }

    fun addNewTask(item: DetailItem) {
        CoroutineScope(Dispatchers.IO).launch {
            TodoApplication.database.filesDao().insertTask(item)
        }
    }

    fun getTasks(id: Int): Flow<MutableList<DetailItem>> {
        return TodoApplication.database.filesDao().getTasks(id)
    }

    fun deleteTask(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            TodoApplication.database.filesDao().deleteTask(id)
        }
    }

    fun updateTask(taskId: Int, status: String) {
        CoroutineScope(Dispatchers.IO).launch {
            TodoApplication.database.filesDao().updateTask(taskId, status)
        }
    }

    fun updateFile(fileId: Int, status: String) {
        CoroutineScope(Dispatchers.IO).launch {
            TodoApplication.database.filesDao().updateFile(fileId, status)
        }
    }

    fun updateAllTasks(fileId: Int, status: String) {
        CoroutineScope(Dispatchers.IO).launch {
            TodoApplication.database.filesDao().updateAllTasks(fileId, status)
        }
    }

}