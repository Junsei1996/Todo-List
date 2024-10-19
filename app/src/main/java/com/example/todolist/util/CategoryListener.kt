package com.example.todolist.util

interface CategoryListener {
    fun onCategorySelected(id:Int)
    fun onAddCategory()
    fun onRemoveCategory(id:Int)
}