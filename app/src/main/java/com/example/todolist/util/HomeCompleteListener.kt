package com.example.todolist.util

import com.example.todolist.model.ListParent

interface HomeCompleteListener {

    fun onComplete(item: ListParent)
    fun onUncheck(item: ListParent)

}