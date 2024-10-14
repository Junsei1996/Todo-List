package com.example.todolist.database

import com.example.todolist.TodoApplication
import com.example.todolist.model.Category
import com.example.todolist.model.DetailItem
import com.example.todolist.model.ListParent
import com.example.todolist.util.Enums
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

object DataRepository {
    fun addNewCategory(cat: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            TodoApplication.database.filesDao().insertCategory(cat)
        }
    }
    fun addNewItem(item: ListParent) {
        CoroutineScope(Dispatchers.IO).launch {
            TodoApplication.database.filesDao().insertFile(item)
        }
    }
    fun getCategories(): Flow<MutableList<Category>> {
        return TodoApplication.database.filesDao().getCategories()
    }
    fun getItems(status:String): Flow<MutableList<ListParent>> {
        return TodoApplication.database.filesDao().getFiles(status)
    }
    fun getItems(catId:Int,status:String): Flow<MutableList<ListParent>> {
        return TodoApplication.database.filesDao().getFilesByCategories(catId,status)
    }
    fun getHistory(): Flow<MutableList<ListParent>> {
        return TodoApplication.database.filesDao().getHistory(Enums.STATUS.COMPLETED.name)
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