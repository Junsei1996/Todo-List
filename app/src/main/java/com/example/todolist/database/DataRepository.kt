package com.example.todolist.database

import com.example.todolist.TodoApplication
import com.example.todolist.model.DetailItem
import com.example.todolist.model.ListParent
import kotlinx.coroutines.flow.Flow

object DataRepository {

    suspend fun addNewItem(item: ListParent) {
        TodoApplication.database.filesDao().insertFile(item)
    }

    suspend fun getItems(): ArrayList<ListParent> {
        return TodoApplication.database.filesDao().getFiles() as ArrayList<ListParent>
    }

    suspend fun deleteItem(id: Int) {
        TodoApplication.database.filesDao().deleteFile(id)
    }

    suspend fun addNewTask(item: DetailItem) {
        TodoApplication.database.filesDao().insertTask(item)
    }

    suspend fun getTasks(id: Int): ArrayList<DetailItem> {
        return TodoApplication.database.filesDao().getTasks(id) as ArrayList<DetailItem>
    }

    suspend fun deleteTask(id: Int) {
        TodoApplication.database.filesDao().deleteTask(id)
    }

    suspend fun updateTask(taskId: Int, status: String) {
        TodoApplication.database.filesDao().updateTask(taskId, status)
    }

    suspend fun updateFile(fileId: Int, status: String) {
        TodoApplication.database.filesDao().updateFile(fileId, status)
    }

    suspend fun updateAllTasks(fileId: Int, status: String) {
        TodoApplication.database.filesDao().updateAllTasks(fileId, status)
    }

}