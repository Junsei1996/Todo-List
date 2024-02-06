package com.example.todolist

import android.app.Application
import com.example.todolist.database.TodoDatabase

class TodoApplication: Application() {

    companion object {
        lateinit var database: TodoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = TodoDatabase.getDataBase(this)
    }
}