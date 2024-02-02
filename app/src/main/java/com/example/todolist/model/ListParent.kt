package com.example.todolist.model

import com.example.todolist.util.Enums

data class ListParent(
    var id: Int,
    var name: String,
    var description: String,
    var status: Enums.STATUS
)
