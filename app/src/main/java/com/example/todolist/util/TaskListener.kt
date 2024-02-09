package com.example.todolist.util

import com.example.todolist.model.DetailItem

interface TaskListener {

    fun onComplete(item: DetailItem)
    fun onUncheck(item: DetailItem)

    fun onDelete(item:DetailItem)

}