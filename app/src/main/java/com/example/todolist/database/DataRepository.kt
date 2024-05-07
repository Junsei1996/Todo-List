package com.example.todolist.database

import androidx.lifecycle.LiveData
import com.example.todolist.TodoApplication
import com.example.todolist.model.DetailItem
import com.example.todolist.model.ListParent
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

object DataRepository {

    fun addNewItem(item: ListParent) {
        TodoApplication.database.filesDao().insertFile(item)
    }

    fun getItems() = flow {
        emit(TodoApplication.database.filesDao().getFiles())
    }

    fun deleteItem(id: Int) {
        TodoApplication.database.filesDao().deleteFile(id)
    }

    fun addNewTask(item: DetailItem) {
        TodoApplication.database.filesDao().insertTask(item)
    }

    fun getTasks(id: Int): ArrayList<DetailItem> {
        return TodoApplication.database.filesDao().getTasks(id) as ArrayList<DetailItem>
    }

    fun deleteTask(id: Int) {
        TodoApplication.database.filesDao().deleteTask(id)
    }

    fun updateTask(taskId: Int, status: String) {
        TodoApplication.database.filesDao().updateTask(taskId, status)
    }

    fun updateFile(fileId: Int, status: String) {
        TodoApplication.database.filesDao().updateFile(fileId, status)
    }

    fun updateAllTasks(fileId: Int, status: String) {
        TodoApplication.database.filesDao().updateAllTasks(fileId, status)
    }

}