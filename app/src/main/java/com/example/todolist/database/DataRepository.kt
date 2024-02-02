package com.example.todolist.database

import com.example.todolist.TodoApplication
import com.example.todolist.model.ListParent
import kotlinx.coroutines.flow.Flow

object DataRepository {

    suspend fun addNewItem(item:ListParent){
        TodoApplication.database.filesDao().insertFile(item)
    }

    suspend fun getItems(): ArrayList<ListParent> {
        return TodoApplication.database.filesDao().getFiles() as ArrayList<ListParent>
    }

    suspend fun deleteItem(id:Int){
        TodoApplication.database.filesDao().deleteFile(id)
    }

}